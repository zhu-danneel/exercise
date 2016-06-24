package chating;

import java.awt.image.BufferedImageFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

public class server extends Thread{
	//声明组件
	ServerSocket serverso;
	Socket clientso;
	public boolean serrun;
	serverframe serview;
	private final int serverport = 8888;
	
	public server() {
		//创建服务器套结字
		try {
			serverso =  new ServerSocket(serverport);
			serrun = true;
			//实例化服务器端的页面
			serview = new serverframe();
			//界面显示服务器信息
			showserinfo();
			//一直运行服务器
			while(true){
				//监听客户端请求链接
				clientso = serverso.accept();
				//创建新线程处理链接到的客户
				new serprocess(clientso, serview);
			}
		}catch(BindException e1){
			 System.out.println("[system] 端口异常！！可能端口占用中！！！");
			 System.out.println("[system] 请查看相关java进程，并尝试重新启动！！！");
			 System.out.println("[system] 具体信息入下:");
			 System.out.println(e1);
		} catch (IOException e2) {
			System.out.println("[system] false to start the server !!!");
			e2.printStackTrace();
		}
		
		//设置运行
		this.start();
	}
	
	
	public void showserinfo(){
		try {
			InetAddress seraddress = InetAddress.getLocalHost();
			byte[] ip = seraddress.getAddress();
			//设置界面显示相关的服务器信息
			serview.sername.setText(seraddress.getHostName());
			serview.serip.setText(seraddress.getHostAddress());
			serview.serport.setText("8888");
			serview.serlog.setText("["+new Date().toString()+"] " +" 服务器启动成功！！！");
			//控制台显示信息
			System.out.println("[system] success to start the server !!!");
			System.out.println("[system] Server IP is:" + (ip[0] & 0xff) + "."
					+ (ip[1] & 0xff) + "." + (ip[2] & 0xff) + "."
					+ (ip[3] & 0xff));
			System.out.println("[system] Server port is:" + serverport);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}	
	}
	
	
	public static void main(String[] args){
		new server();
	}	
	
}

class serprocess extends Thread{
	private Socket clientso;
	private BufferedReader in;
	private PrintWriter out;
	
	private static Vector onlineall = new Vector(10, 5);
	private static Vector socketall = new Vector(10, 5);
	
	private String receive, key;
	private StringTokenizer st;
	
	private final String usrfile = "/Users/Danneels/Desktop/info.txt";
	private serverframe serview;
	
	public serprocess(Socket clientso, serverframe serview){
		this.clientso = clientso;
		this.serview = serview;
		
		try {
			//创建输入输出流
			in = new BufferedReader(new InputStreamReader(clientso.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientso.getOutputStream())),true);
			//启动线程处理客户端的请求
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		try {
			while(true){
				//接受信息包
				receive = in.readLine();
				//获取消息包的第一个信息
				st = new StringTokenizer(receive, "|");
				key = st.nextToken();
				if (key.equals("login")){
					//登录
					login();
				}
				else if (key.equals("talk")){
					//聊天
					talk();
				}
				else if(key.equals("reg")){
					//注册
					register();
				}	
				else if (key.equals("init")){
					//初始化聊天在线列表
					renewonline();
				}
			}
		} catch (Exception e) {
			//捕获客户端关闭带来的异常
			String leave = closeone();
			Date t = new Date();
			//服务器显示离开信息
			add("[" + t.toString() + "] " + leave + " is out !!!");
			//更新服务器关于上线人员的显示
			renewonline();
			System.out.println("[1]"+leave + ":is out !!");
			//向所有成员发送离线消息
			sendall("remove|"+leave);
			sendall("talk|" + "[" + t.toString() + "] "+ "user：" + leave + " is out !!!");
		}	
	}
	
	//登录处理
	private void login(){
		//获取信息包的后两个信息
		String name = st.nextToken();
		String pwd = st.nextToken().trim();
		
		boolean succeed = false;
		 Date t = new Date();
		 add("["+t.toString()+"]  "+name + " is loading... passwd is "+ pwd+"   port is " + clientso.getPort());
		 System.out.println("[log] " + name + ":" + pwd + ":"+ clientso.getPort());
		 //判断已经登录的集合里面是否 重合
		 for (int i = 0; i < onlineall.size();i++){
			 //重复登录
			 if (onlineall.elementAt(i).equals(name)){
				 System.out.println("[log] " + name + " is logined!");
				 //客户端发送信息
				 out.println("login|" + name + " is logined!");
				 //更新服务器的日志
				 add("["+t.toString()+"]  "+name + " is logined! And it try to login twice !!!");
				 return;
			 }
		 }
		 //判断是否密码正确，信息匹配满足登录,是否注册过
		 if (canlogin(name, pwd)){
			 userlogin(name);
			 succeed = true;
		 }
		 if (!succeed){
			 out.println("login|" + name + " fail to login!! for not register!!");
			 add("["+t.toString()+"]  "+ name + " fail to login!! for not register!!s");
			 System.out.println("[system] " + name + " fail to login!! for not register!!");
		 }
		 
	}
	//判断是否已经注册
	private boolean canlogin(String name, String pwd){
		String read;
		FileInputStream inputtofile = null;
		DataInputStream inputtodata = null;
		try {
			inputtofile = new FileInputStream(usrfile);
			inputtodata = new DataInputStream(inputtofile);
			//读取注册的文件信息，看是否有符合的信息条
			while((read = inputtodata.readLine()) != null){
				if (read.equals(name + "|" + pwd )){
					return true;
				}
			}
		} catch (FileNotFoundException e1) {
			System.out.println("[ERROR] User File has not exist!" + e1);
			//服务器发送警告消息
			out.println("error|[file] somthing wrong when try to login !!!");
		}catch (IOException e2) {
			System.out.println("[ERROR] User File fail to read!" + e2);
			out.println("error|[file] somthing wrong when try to login !!!");
		}
		return false;
	}
	//聊天处理
	private void talk(){
		Socket socketsend;
		PrintWriter outsend = null;
		Date t = new Date();
		//聊天信息，发送者，接受者
		String talkinfo = st.nextToken();
		String sendone = st.nextToken();
		String receive = st.nextToken();
		//服务器显示聊天信息
		add("[" + t.toString() + "] "+ sendone + " @ " + receive + ":" + talkinfo);
		if (receive.equals("all")){
			for (int i = 0; i < onlineall.size();i++){
				if (!sendone.equals(onlineall.elementAt(i))){
					socketsend = (Socket) socketall.elementAt(i);
					try {
						outsend = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketsend.getOutputStream())),true);
					} catch (IOException e) {
						e.printStackTrace();
					}
					outsend.println("talk|"+sendone +" said to all :  " + talkinfo);
				}
			}
		}
		else{
		//将信息发送给相关用户
		for (int i = 0; i < onlineall.size();i++){
			if (receive.equals(onlineall.elementAt(i)) && !sendone.equals(onlineall.elementAt(i))){
				socketsend = (Socket) socketall.elementAt(i);
				try {
					outsend = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketsend.getOutputStream())),true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				outsend.println("talk|"+ sendone + " said :  " + talkinfo);
			}
		}
		}
	}
	//注册处理
	private void register(){
		//解析得到注册信息
		String name = st.nextToken();
		String psswd = st.nextToken().trim();
		Date t = new Date();
		System.out.println("[reg] " + name + ":" + psswd + ":"+ clientso.getPort());
		add("[" + t.toString()+"] " + name + " is trying to register!!");
		//判断是否已经存在
		if(haveexist(name)){
			System.out.println("[reg] " + name + " Register fail!");
			add("[" + t.toString()+"] " + name + " fail to register !! it is already exist!");
			out.println("reg|already exist!");
		}
		else{
			//不存在，处理用户注册登录
			try {
				//保存用户信息(文件)
				RandomAccessFile userfile = new RandomAccessFile(usrfile, "rw");
				userfile.seek(userfile.length());
				userfile.writeBytes(name + "|"+psswd +"\r\n");
				add("[" + t.toString()+"] " + name + " is registered !!");
				out.println("reg|succeed");
				//注册成功，接着登录
				userlogin(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private boolean haveexist(String name) {
		String strRead;
		try {
			FileInputStream inputfile = new FileInputStream(usrfile);
			DataInputStream inputdata = new DataInputStream(inputfile);
			while ((strRead = inputdata.readLine()) != null) {
				StringTokenizer stUser = new StringTokenizer(strRead, "|");
				if (stUser.nextToken().equals(name)) {
					return true;
				}
			}
		} catch (FileNotFoundException fn) {
			System.out.println("[ERROR] User File has not exist!" + fn);
			out.println("error|读写文件时出错!");
		} catch (IOException ie) {
			System.out.println("[ERROR] " + ie);
			out.println("error|读写文件时出错!");
		}
		return false;
	}

	//用户成功登录,给用户添加选项以及发送上线信息
	private void userlogin(String name){
		//允许用户登录成功
		out.println("login|succeed");
		//各用户聊天添加选项
		sendall("online|"+name);
		//在线集合里面添加成员
		onlineall.addElement(name);
		socketall.addElement(clientso);
		//时间
		Date t = new Date();
		//添加登录日志
		add("[" + t.toString()+"] " + name + " login succeed !!!");
		//更新在线列表
		renewonline();
		System.out.println("[log] " + name + ":login succeed!");
	}
	//离开聊天室列表删除信息,将其在socketvector中删除
	private String closeone(){
		String name = null;
		for (int i = 0;i < socketall.size();i++){
			//匹配要删除的套接字
			if(clientso.equals(socketall.elementAt(i))){
				name = onlineall.elementAt(i).toString();
				//删除集合里面的对应信息
				socketall.removeElementAt(i);
				onlineall.removeElementAt(i);
				//更新在线列表人数
				renewonline();
			}
		}
		try {
			in.close();
			clientso.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}
	//服务端日志添加显示信息
	private void add(String info){
		String infoall = serview.serlog.getText() + "\n" + info;
		serview.serlog.setText(infoall);
	}
	//更新服务器关于上线人员的显示
	private void renewonline(){
		String online = "online";
		String[] listuser = new String[50];
		String username = null;
		
		for (int i = 0; i < onlineall.size();i++){
			online += "|" + onlineall.elementAt(i);
			username = " " + onlineall.elementAt(i);
			listuser[i] = username;
		}
		//新对话的初始化聊天成员列表
		out.println(online);
		//显示服务器人数
		serview.onnumber.setText("" + onlineall.size());
		//显示在线列表
		serview.listmember.setListData(onlineall);
		System.out.println("[9]"+online);
	}
	//向所有成员发送离线消息
	private void sendall(String toall){
		Socket sotosend;
		PrintWriter outsend;
		try {
			//获取发送的套接字
			for (int i = 0; i < socketall.size(); i++){
				sotosend = (Socket) socketall.elementAt(i);
				outsend = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sotosend.getOutputStream())),true);
				outsend.println(toall);
			}
		} catch (Exception e) {
			System.out.println("[6]"+"send error!!!!");
			e.printStackTrace();
		}
	}	
}
