package WC;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

public class wcGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	JPanel contentPane=new JPanel();//设置面板
	
	JLabel label_1= new JLabel("点击获取要查询的文件名：");//用户名标签

	static JButton button_0= new JButton("查询");//进入主界面
	static JButton button_1= new JButton("退出");//退出系统
	static JTextArea text = new JTextArea();
	//默认路径为java下的test文件
	static JTextField filetext=new JTextField("C:/mycode/test/normal.java");
	static JButton button=new JButton("浏览");
	
	public void GUI(){
		setTitle("WC.exe");
	    setSize(400, 300);   //设置大小 
	    setLocationRelativeTo(null); //使整个窗口显示在屏幕中央
		setResizable(false);        //设置窗体不可改变大小
		setContentPane(contentPane);//设定一个容器，往容器中添加组件
		contentPane.setLayout(null);//不使用流式布局
	    //往面板中添加组件
	    contentPane.add(label_1);
	    contentPane.add(button_1);
	    contentPane.add(button_0);
	    contentPane.add(text);
	    contentPane.add(button);
	    contentPane.add(filetext);
	   
	    //设置各组件大小方位，以及字体，背景颜色
	    label_1.setBounds(10,0,180,50); 
	      //fileChooser.setBounds(20, 70, 350, 50);
	    filetext.setBounds(10, 40, 200, 30);
	    filetext.setBackground(new Color(128,138,135));
	    button.setBounds(220, 40, 60, 30);
	    button_0.setBounds(290, 40, 60, 30);
	    text.setBounds(10, 90, 300, 120);
	    text.setBackground(new Color(100,250,250));
	    text.setFont(new Font("黑体", Font.BOLD, 15));
	    text.setFocusable(isFocusable());
	    button_1.setBounds(290, 230, 60, 30);
	    setVisible(true);   //设置可见
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //点关闭按钮时退出 
	    //获取选择的路径

	//}
	//public static void main(String[] args) {
	//	wcGUI wc=new wcGUI();
	//	 wc.GUI();
	
	//为空户浏览注册点击事件，使得用户浏览时弹出文件选择框，用户选择文件后
	button.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		//设置默认弹出的目录文件夹
		File file=new File("C:/mycode/test");
		//TODO Auto-generated method stub
		JFileChooser filechooser=new JFileChooser();
		filechooser.setFileSelectionMode(filechooser.FILES_ONLY);//只需选择文件
		filechooser.setCurrentDirectory(file);
		filechooser.showDialog(new Label(), "选择");
		File selfile=filechooser.getSelectedFile();
		//将该文件绝对路径放入文本框中
		filetext.setText(selfile.getAbsolutePath().toString());
	}
	});
	//为查询按钮注册点击事件，使得用户点击查询时，文本框会出现文件的统计信息
	button_0.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//获取文件路径
			String path=filetext.getText();
		    File newfile=new File(path);
		    Count count=new Count();
		    //获取到的内容放入文本框中,用了setText后再次查询可以覆盖以前的记录
		   String str="字符数为："+count.countChar(newfile)+"\n"+"单词数为："+count.countWords(newfile)+"\n"+"行数："+count.countLines(newfile)+"\n";
		   text.setText(str);
		   text.append("空行数为："+count.specialLines(newfile)[0]+"\n");
		   text.append("代码行数为："+count.specialLines(newfile)[1]+"\n");
		   text.append("注释行数为："+count.specialLines(newfile)[2]+"\n");
		
		}
	});
	
	button_1.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	});
	}
	
}
