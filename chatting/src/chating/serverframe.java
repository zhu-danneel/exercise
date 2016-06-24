package chating;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class serverframe extends JFrame implements ActionListener{
	JTabbedPane tpserver;
	//第一块
	JPanel serverup, servermid, serverdown,server;
	JLabel onlinenumber, servername, serverip, serverport,serverlog;
	JTextField onnumber, sername,serip,serport;
	JTextArea serlog;
	JButton serclose, sersave;
	//第二块
	JPanel menlist;
	JScrollPane scmember;
	JLabel listallmenber;
	JList listmember;
	JButton listclose;
	//第三块
	JPanel about;
	JLabel logoinfo,logo;
	JLabel easy1, good,good1,good2,good3,good4,address; 
	JTextArea easy2;
	
	public serverframe() {
		//大架架
		super("EASY康泰聊天系统--server");
		setSize(550,550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//设置居中显示
		Dimension win = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension my = this.getSize();
		if (my.width > win.width)
		{
			my.width = win.width;
		}
		if (my.height > win.height)
		{
			my.height = win.height;
		}
		this.setLocation((win.width - my.width)/2, (win.height - my.height)/2);
		
	
		//实例化组件
		
	////////////////////////////第一版面////////////////////////////////
		//实例化上面板
		serverup = new JPanel(new GridLayout(2, 4));
		serverup.setBackground(new Color(23, 54, 78));
		serverup.setFont(new Font("宋体", 0, 12));
		//实例化上面板组件
		onlinenumber = new JLabel("当前在线人数：",JLabel.CENTER);
		onlinenumber.setForeground(new Color(244, 225, 225));
		onlinenumber.setFont(new Font("宋体", 0, 12));
		onnumber = new JTextField(10);
		onnumber.setBackground(new Color(211, 230, 249));
		onnumber.setFont(new Font("宋体", 0, 12));
		onnumber.setEditable(false);

		servername = new JLabel("服务器名：",JLabel.CENTER);
		servername.setForeground(new Color(244, 225, 225));
		servername.setFont(new Font("宋体", 0, 12));
		sername = new JTextField(10);
		sername.setBackground(new Color(211, 230, 249));
		sername.setFont(new Font("宋体", 0, 12));
		sername.setEditable(false);
			
		serverip = new JLabel("服务器ip：",JLabel.CENTER);
		serverip.setForeground(new Color(244, 225, 225));
		serverip.setFont(new Font("宋体", 0, 12));
		serip = new JTextField(10);
		serip.setBackground(new Color(211, 230, 249));
		serip.setFont(new Font("宋体", 0, 12));
		serip.setEditable(false);
		
		serverport = new JLabel("服务器端口：",JLabel.CENTER);
		serverport.setForeground(new Color(244, 225, 225));
		serverport.setFont(new Font("宋体", 0, 12));
		serport = new JTextField("8888",10);
		serport.setBackground(new Color(211, 230, 249));
		serport.setFont(new Font("宋体", 0, 12));
		serport.setEditable(false);
		//上面板组合
		serverup.setBounds(0, 0, 500, 100);
		serverup.add(servername);
		serverup.add(serverip);
		serverup.add(serverport);
		serverup.add(onlinenumber);
		serverup.add(sername);
		serverup.add(serip);
		serverup.add(serport);
		serverup.add(serport);
		serverup.add(onnumber);
		
		
		//中间组件实例化
		//实例化中间面板
		servermid = new JPanel(new FlowLayout());
		servermid.setBackground(new Color(23, 54, 78));
		//实例化中间组件
		serverlog = new JLabel("＊服务器日志：");
		serverlog.setForeground(new Color(244, 225, 225));
		serverlog.setFont(new Font("宋体", 0, 20));
		serlog = new JTextArea(24,45);
		serlog.setWrapStyleWord(true);
		serlog.setLineWrap(true);
		serlog.setFont(new Font("宋体", 0, 12));
		serlog.setForeground(new Color(4, 84, 206));
		serlog.setEditable(false);
		JScrollPane s = new JScrollPane(serlog);
		//中间面板组合
		servermid.add(serverlog);
		servermid.add(s);
		
		
		//下面组件实例化
		//实例化下面面板
		serverdown = new JPanel(new BorderLayout());
		serverdown.setBackground(new Color(23, 54, 78));
		//实例化下面组件
		serclose = new JButton("关闭服务器");
		serclose.setFont(new Font("宋体", 0, 12));
		serclose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeServer();
			}
		});
		sersave = new JButton("保存日志");
		sersave.setFont(new Font("宋体", 0, 12));
		sersave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveLog();
			}
		});
		//组合下面组件
		serverdown.add(serclose,BorderLayout.WEST);
		serverdown.add(sersave,BorderLayout.EAST);
		
		//整合服务器面板
		//实例化server面板
		server = new JPanel(new BorderLayout());
		server.add(serverup,BorderLayout.NORTH);
		server.add(servermid, BorderLayout.CENTER);
		server.add(serverdown, BorderLayout.SOUTH);
		
	////////////////////////////第二版面////////////////////////////////
		//实例化第二面板
		menlist = new JPanel(null);
		menlist.setBackground(new Color(23, 54, 78));
		menlist.setFont(new Font("宋体", 0, 12));
		//实例化第二面板组件
		listallmenber = new JLabel("当前在线成员列表：",JLabel.CENTER);
		listallmenber.setForeground(new Color(244, 225, 225));
		listallmenber.setFont(new Font("宋体", 0, 18));
		listmember = new JList();
		listmember.setFont(new Font("宋体", 0, 12));
		listmember.setVisibleRowCount(18);
		listmember.setFixedCellWidth(200);
		listmember.setFixedCellHeight(20);
		scmember = new JScrollPane();
		scmember.setBackground(Color.CYAN);
		scmember.setFont(new Font("宋体", 0, 12));
		scmember.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scmember.getViewport().setView(listmember);
		
		listclose = new JButton("关闭服务器");
		listclose.setFont(new Font("宋体", 0, 12));
		listclose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeServer();
			}
		});
		
		//组合第二面板
		listallmenber.setBounds(155, 20, 180, 25);
		menlist.add(listallmenber);
		scmember.setBounds(100, 50, 300, 350);
		menlist.add(scmember);
		listclose.setBounds(200, 425, 110, 35);
		menlist.add(listclose);
		
		
		
	////////////////////////////第三版面////////////////////////////////
		//实例化
		about = new JPanel(new GridLayout(11,1));
		about.setBackground(new Color(23, 54, 78));
		about.setFont(new Font("宋体", 0, 12));
		//实例化组件
		logoinfo = new JLabel(new ImageIcon("imgs/logo.png"));
		logo = new JLabel(new ImageIcon("imgs/5.png"));
		easy1 = new JLabel("ABOUT EASY",JLabel.CENTER);
		easy1.setForeground(new Color(66, 211, 253));
		easy1.setFont(new Font("宋体", 1, 20));
		easy2 = new JTextArea("       创新科技团队，朝气、高素质、年轻化、专业化的研发团队."
				+ "为公司蓬勃发展提供源源不断的活力与动力。 工本科以上科技人员占100%，"
				+ "软件技术人员占100%。除有常年外聘的专家顾问外，还汇聚了信息化管理领域的技术精英，"
				+ "为信息服务平台的研究和开发提供了长期的、源源不断地支持。");
		easy2.setLineWrap(true);
		easy2.setEditable(false);
		easy2.setBackground(new Color(23, 54, 78));
		easy2.setForeground(new Color(249, 247, 245));
		easy2.setFont(new Font("宋体", 0, 12));
		good = new JLabel("我们的研发团队擅长于",JLabel.CENTER);
		good.setForeground(new Color(66, 211, 253));
		good.setFont(new Font("宋体", 1, 20));
		good1 = new JLabel("JAVA分布式系统以及JAVA应用系统的解决方案、数据库分析等",JLabel.CENTER);
		good1.setForeground(new Color(249, 247, 245));
		good1.setFont(new Font("宋体", 0, 12));
		good2 = new JLabel("移动互联网应用解决方案，擅长于Andriod、IOS、PAD程序开发",JLabel.CENTER);
		good2.setForeground(new Color(249, 247, 245));
		good2.setFont(new Font("宋体", 0, 12));
		good3 = new JLabel(".net、网站建设、网络安全、通讯技术等",JLabel.CENTER);
		good3.setForeground(new Color(249, 247, 245));
		good3.setFont(new Font("宋体", 0, 12));
		good4 = new JLabel("各种各种，只有你想不到，没有我们做不到",JLabel.CENTER);
		good4.setForeground(new Color(249, 247, 245));
		good4.setFont(new Font("宋体", 0, 12));
		address = new JLabel("陕西省西安市西安邮电大学",JLabel.CENTER);
		address.setForeground(new Color(66, 211, 253));
		address.setFont(new Font("宋体", 0, 16));
		
		
		//组合面板三
		about.add(logoinfo);
		about.add(easy1);
		about.add(easy2);
		about.add(good);
		about.add(good1);
		about.add(good2);
		about.add(good3);
		about.add(good4);
		about.add(logo);
		about.add(address);
		
		
		
		
		//主面板组合
		//主面板实例化
		tpserver = new JTabbedPane(JTabbedPane.TOP);
		tpserver.setBackground(new Color(23, 54, 78));
		tpserver.setFont(new Font("宋体", 0, 12));
		//主面板组合
		tpserver.add("服务器信息", server);
		tpserver.add("在线人员列表", menlist);
		tpserver.add("关于我们", about);
		
		//设置显示
		this.getContentPane().add(tpserver);
		setVisible(true);
		
	}
	
	
	
	protected void  closeServer() {
		this.dispose();
		System.out.println("[system] success to close the server !!!");
		System.exit(0);
		
	}
	protected void  saveLog() {
		try{
			FileOutputStream fo = new FileOutputStream("loginfo.txt", true);
			String temp = serlog.getText();
			fo.write(temp.getBytes());
			fo.close();
			//显示提示信息
			JOptionPane.showMessageDialog(null, "已经保存当前登录，交流信息！位于loginfo.txt");
		}catch(Exception e){
			String str = "something error！！\n"+e.getStackTrace();
			JOptionPane.showMessageDialog(null,str);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	public static void main(String[] args)
	{
		new serverframe();
	}
	

}
