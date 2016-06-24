package chating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class clientframe extends JFrame implements ActionListener{
	JTabbedPane tpclient;
	//登录
	JPanel plogin;
	JLabel loginname, loginpwd;
	JTextField logname;
	JPasswordField logpwd;
	JButton login, reg, exit;
	JLabel logo;
	//关于我们
	JPanel about;
	JLabel logoinfo;
	JLabel easy1, good,good1,good2,good3,address; 
	JTextArea easy2;
	
	
	//业务
	Socket so;
	BufferedReader in;
	PrintWriter out;
	
	String strtosend, strfromrecieve, key, status;
	private StringTokenizer st;
	
	public clientframe() {
		super("EASY康泰聊天系统--client");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//设置位置
		Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();// 在屏幕居中显示
		Dimension fra = this.getSize();
		if (fra.width > scr.width) {
			fra.width = scr.width;
		}
		if (fra.height > scr.height) {
			fra.height = scr.height;
		}
		this.setLocation((scr.width - fra.width) / 2,
				(scr.height - fra.height) / 2);

	//////////////////////登录/////////////////////////////
		//实例化登录面板
		plogin = new JPanel(null);
		plogin.setBackground(new Color(23, 54, 78));
		plogin.setFont(new Font("宋体", 0, 12));
		//实例化登录组件
		logo =new JLabel(new ImageIcon("imgs/logobig.png"));
		logo.setBounds(100, 15, 180, 50);
		loginname = new JLabel("登录名:",JLabel.RIGHT);
		loginname.setBounds(45, 75, 80, 40);
		loginname.setForeground(new Color(244, 225, 225));
		loginname.setFont(new Font("宋体", 0, 12));
		logname = new JTextField(10);
		logname.setBounds(135, 80, 120, 30);
		logname.setBackground(Color.white);
		logname.setFont(new Font("宋体", 0, 12));
		
		loginpwd = new JLabel("密码:",JLabel.RIGHT);
		loginpwd.setBounds(45, 120, 80, 40);
		loginpwd.setForeground(new Color(244, 225, 225));
		loginpwd.setFont(new Font("宋体", 0, 12));
		logpwd = new JPasswordField(10);
		logpwd.setBounds(135, 125, 120, 30);
		logpwd.setBackground(Color.white);
		logpwd.setFont(new Font("宋体", 0, 12));
		
		login = new JButton("登录");
		login.setBounds(90, 180, 90, 40);
		login.setFont(new Font("宋体", 0, 12));
		reg = new JButton("注册");
		reg.setBounds(200, 180, 90, 40);
		reg.setFont(new Font("宋体", 0, 12));
		
		//添加监听事件
		login.addActionListener(this);
		reg.addActionListener(this);
		
		
		//组合登录面板
		plogin.add(logo);
		plogin.add(loginname);
		plogin.add(logname);
		plogin.add(loginpwd);
		plogin.add(logpwd);
		plogin.add(login);
		plogin.add(reg);
		
		
	////////////////////关于我们////////////////////
		//实例化
		about = new JPanel(new GridLayout(8,1));
		about.setBackground(new Color(23, 54, 78));
		about.setFont(new Font("宋体", 0, 10));
		//实例化组件
		logoinfo = new JLabel(new ImageIcon("imgs/logo.png"));
		easy1 = new JLabel("ABOUT EASY",JLabel.CENTER);
		easy1.setForeground(new Color(66, 211, 253));
		easy1.setFont(new Font("宋体", 1, 14));
		easy2 = new JTextArea("       创新科技团队，朝气、高素质、年轻化、专业化的研发团队."
+ "为公司蓬勃发展提供源源不断的活力与动力。 工本科以上科技人员占100%，"
+ "软件技术人员占100%。除有常年外聘的专家顾问外，还汇聚了信息化管理领域的技术精英，"
+ "为信息服务平台的研究和开发提供了长期的、源源不断地支持。");
		easy2.setLineWrap(true);
		easy2.setEditable(false);
		easy2.setBackground(new Color(23, 54, 78));
		easy2.setForeground(new Color(249, 247, 245));
		easy2.setFont(new Font("宋体", 0, 8));
		good = new JLabel("我们的研发团队擅长于",JLabel.CENTER);
		good.setForeground(new Color(66, 211, 253));
		good.setFont(new Font("宋体", 1, 14));
		good1 = new JLabel("JAVA分布式系统以及JAVA应用系统的解决方案、数据库分析等",JLabel.CENTER);
		good1.setForeground(new Color(249, 247, 245));
		good1.setFont(new Font("宋体", 0, 10));
		good2 = new JLabel("移动互联网应用解决方案，擅长于Andriod、IOS、PAD程序开发",JLabel.CENTER);
		good2.setForeground(new Color(249, 247, 245));
		good2.setFont(new Font("宋体", 0, 10));
		good3 = new JLabel(".net、网站建设、网络安全、通讯技术等",JLabel.CENTER);
		good3.setForeground(new Color(249, 247, 245));
		good3.setFont(new Font("宋体", 0, 10));
		address = new JLabel("陕西省西安市西安邮电大学",JLabel.CENTER);
		address.setForeground(new Color(66, 211, 253));
		address.setFont(new Font("宋体", 0, 14));


		//组合面板三
		about.add(easy1);
		about.add(easy2);
		about.add(good);
		about.add(good1);
		about.add(good2);
		about.add(good3);
		about.add(logoinfo);
		about.add(address);
		
		
		//主面板组合
		//主面板实例化
		tpclient = new JTabbedPane(JTabbedPane.TOP);
		tpclient.setBackground(new Color(23, 54, 78));
		tpclient.setFont(new Font("宋体", 0, 12));
		//主面板组合
		tpclient.add("登录",plogin);
		tpclient.add("关于我们",about);
			
		//设置显示
		this.getContentPane().add(tpclient);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		try {
			if (obj.equals(login)){
				//检查信息完全
				if ((logname.getText().length() > 0) && (logpwd.getText().length() > 0)){
					//链接套接字
					connectsocket();
					//合成要发送的信息
					strtosend = "login|" + logname.getText() + "|" + String.valueOf(logpwd.getPassword());
					//发送登录消息
					out.println(strtosend);
					//接受服务器消息，进一步处理登录
					initlogin();
				}
				else{
					//信息不完全，弹出提示
					JOptionPane.showMessageDialog(this, "please input the compelete infimation", "there is something wrong !!!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else if (obj.equals(reg)){
				//判断信息完全
				if ((logname.getText().length() > 0) && (logpwd.getText().length() > 0 )){
					//链接套接字
					connectsocket();
					//合成要发送的信息
					strtosend = "reg|" + logname.getText() + "|" + String.valueOf(logpwd.getPassword());
					//发送登录消息
					out.println(strtosend);
					//接受服务器消息，进一步处理登录
					initlogin();
				}
				else{
					//信息不完全，弹出提示
					JOptionPane.showMessageDialog(this, "please input the compelete infimation", "there is something wrong !!!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (Exception e2) {
			System.out.println("{1}"+e);
		}
	}
	
	protected void connectsocket() {
		try {
			so = new Socket("127.0.0.1", 8888);
			in = new BufferedReader(new InputStreamReader(so.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(so.getOutputStream())),true);
		} catch (Exception e) {
			this.dispose();
			//链接服务器端失败
			System.out.println("{2}"+e);
			JOptionPane.showMessageDialog(this, "faile to connect the server !!!", " There is something wrong !!!",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
	private void initlogin(){
		try {
			strfromrecieve = in.readLine();
			System.out.println("{3}"+strfromrecieve);
			st = new StringTokenizer(strfromrecieve, "|");
			key = st.nextToken();
			//登录返回信息处理
			if (key.equals("login")){
				status = st.nextToken();
				//匹配服务器成功登录的信息
				if (status.equals("succeed")){
					this.dispose();
					//创建新的线程进行聊天
					chatframe win = new chatframe(logname.getText());
					out.println("init|online");
					//给发送按钮添加监听事件
					win.sendto.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (win.txt.getText().length() > 0) {
								out.println("talk|" + win.txt.getText() + "|"
										+ logname.getText() + "|"
										+ win.listonline.getSelectedItem().toString());
								win.txtchat.setText(win.txtchat.getText()+"\r\n  " + "you said to "+win.listonline.getSelectedItem().toString()+ " : " + win.txt.getText());
								win.txt.setText("");
							}
						}
					});
					new clientchat(so,win);
				}
				else{
					this.dispose();
					JOptionPane.showMessageDialog(null, "the info is: "+ status +"!\n fail to login !!!\n please try again later !!!", "fail to login !!",JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
			}
			else if(key.equals("reg")){
				//注册返回信息处理
				status = st.nextToken();
				//匹配服务器成功注册的信息
				if (status.equals("succeed")){
					this.dispose();
					JOptionPane.showMessageDialog(null, "you have succeed to register and now to login !!!!!\n ", "succeed to register !!",JOptionPane.INFORMATION_MESSAGE);
					//创建新的线程进行聊天
					chatframe win = new chatframe(logname.getText());
					while(st.hasMoreTokens()){
						win.listonline.addItem(st.nextToken());
					}
					out.println("init|online");
					//进程处理聊天消息
					new clientchat(so,win);
				}
				else{
					//注册失败显示消息状态
					this.dispose();
					JOptionPane.showMessageDialog(null, "you fail to regiter !!!!\n"+"the info is:"+ status +"\nplease try again later !!!", "fail to register !!",JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
			}
			else if (key.equals("error")){
				status = st.nextToken();
				JOptionPane.showMessageDialog(this,status, "error !!!",JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	//聊天进程
	class clientchat implements Runnable{
		private Socket so;
		private  BufferedReader in;
		private PrintWriter out;
		private String strfromrecieve, key;
		private Thread chatthread;
		private StringTokenizer st;
		//开启新的聊天框
		chatframe win;
		
		public clientchat(Socket so,chatframe win) {
			this.win = win;
			this.so = so;
			try {
				in = new BufferedReader(new InputStreamReader(so.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//创建并启动线程处理业务
			chatthread = new Thread(this);
			chatthread.start();
		}
		
		@Override
		public void run() {
			while(true){
				synchronized (this) {
					try {
						strfromrecieve = in.readLine();
						System.out.println(strfromrecieve);
					} catch (IOException e) {
						e.printStackTrace();
					}
					st = new StringTokenizer(strfromrecieve, "|");
					key = st.nextToken();
					if (key.equals("talk")){
						
						String talk = st.nextToken();
						System.out.println(talk);
						talk = win.txtchat.getText() + "\r\n  " + talk;
						win.txtchat.setText(talk);
					}
					else if (key.equals("online")){
						String stroline;
						//将服务器发来的在线人员信息包加入到可选里面
						while (st.hasMoreTokens()){
							stroline = st.nextToken();
							if(!stroline.equals(logname.getText()))
							{
								win.listonline.addItem(stroline);
							}		
						}
					}
					else if (key.equals("remove")){
						String strremove;
						while(st.hasMoreTokens()){
							strremove = st.nextToken();
							win.listonline.removeItem(strremove);
						}
					}
					else if (key.equals("warning")){
						String strwarning;
						strwarning = st.nextToken();
						JOptionPane.showMessageDialog(null, "warning !!!");
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args){
		 new clientframe();
	}

}
