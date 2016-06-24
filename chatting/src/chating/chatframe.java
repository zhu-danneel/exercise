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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class chatframe extends JFrame implements ActionListener{
	JTabbedPane two;
	
	JPanel pchatdown,pchat,pp;
	JScrollPane scchattxt;
	JTextArea txtchat;
	JTextField txt;
	JButton sendto, exit;
	JLabel send, tosend;
	JComboBox listonline;
	
	JPanel about;
	JLabel logoinfo,logo;
	JLabel easy1, good,good1,good2,good3,good4,address; 
	JTextArea easy2;
	
	public chatframe(String name) {
		//设置窗体信息
		super(name+"--聊天");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
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
		
		
		//实例化大面板
		two = new JTabbedPane(JTabbedPane.TOP);
		two.setBackground(new Color(23, 54, 78));
		two.setFont(new Font("宋体", 0, 12));

		//组件实例化
		//小大面板实例化
		pchat = new JPanel(null);
		pchat.setFont(new Font("宋体", 0, 12));
		pchat.setBackground(new Color(23, 54, 78));
		//实例化聊天面板
		pp = new JPanel(null);
		pp.setBackground(new Color(23, 54, 78));
		pp.setFont(new Font("宋体", 0, 12));
		txtchat = new JTextArea(24, 40);
		txtchat.setFont(new Font("宋体", 1, 14));
		txtchat.setBackground(Color.white);
		txtchat.setWrapStyleWord(true);
		txtchat.setLineWrap(true);
		txtchat.setForeground(new Color(4, 84, 206));
		txtchat.setEditable(false);
		scchattxt = new JScrollPane();
		scchattxt.setBackground(Color.white);
		scchattxt.setFont(new Font("宋体", 0, 12));
		scchattxt.getViewport().setView(txtchat);
		pp.add(scchattxt);
		//下面组件
		//下面面板实例化
		pchatdown = new JPanel(new FlowLayout());
		pchatdown.setBackground(new Color(23, 54, 78));
		pchatdown.setFont(new Font("宋体", 0, 12));
		//下面面板组件实例化
		send = new JLabel("发言：", JLabel.RIGHT);
		send.setForeground(new Color(244, 225, 225));
		send.setFont(new Font("宋体", 0, 14));
		txt = new JTextField(16);
		txt.setFont(new Font("宋体", 0, 12));
		tosend = new JLabel("@",JLabel.RIGHT);
		tosend.setFont(new Font("宋体", 0, 14));
		tosend.setForeground(new Color(244, 225, 225));
		listonline = new JComboBox();
		listonline.addItem("all");
		sendto = new JButton("发送");
		sendto.setFont(new Font("宋体", 1, 14));
		exit = new JButton("退出");
		exit.setFont(new Font("宋体", 3, 14));
		
		//按钮添加监听事件
		exit.addActionListener(this);
		
		//组合下面板
		pchatdown.add(send);
		pchatdown.add(txt);
		pchatdown.add(tosend);
		pchatdown.add(listonline);
		pchatdown.add(sendto);
		pchatdown.add(exit);
		
		//组合大面板
		scchattxt.setBounds(10, 10, 760, 450);
		pchat.add(scchattxt);
		pchatdown.setBounds(20, 480, 780, 30);
		pchat.add(pchatdown);
		
		//信息面板
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
		
		//组合选择面板
		two.add("聊天", pchat);
		two.add("关于我们",about);
		
		//设置显示
		this.setContentPane(two);
		setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(exit)){
			System.exit(0);
		}
	}
	
	public static void main(String[] args){
		new chatframe("314");
	}

}
