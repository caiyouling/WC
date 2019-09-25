package WC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Main {
	public static void main(String[] agrs) {
		//设置默认的文件夹路径，以后测试的文件都放在该文件夹下
		Count tool=new Count();
		dirList dir=new dirList();
			//使用工具类中的countChar方法
			//获取用户输入的指令
			System.out.println("--------欢迎进入WC.exe界面------------");
			System.out.println("用法： wc.exe [-c] [-w] [-l] [-s] [-a] [-x] [-o] filename");
			System.out.println("选项：");
			System.out.println("-c 返回文件filename中的字符数");
			System.out.println("-w 返回文件filename中的单词数");
			System.out.println("-l 返回文件filename中的行数");
			System.out.println("-s 返回该目录下所有文件的数据信息");
			System.out.println("-a 返回该文件的空行数，代码行数，注释行数");
			System.out.println("-x 显示图形界面");
			System.out.println("-o,结束查询");
			System.out.println("please enter the commad:\n");
			System.out.println("首先，请先输入想要查询的文件路径(文件夹路径哦！)：\n");
			Scanner in=new Scanner(System.in);
			String path=in.nextLine();
			File file=new File(path);
			if(file.exists()) {
				System.out.println("请按用法格式继续输入指令(例：wc.exe -l test.java):");
			while(true) {
			Scanner scan=new Scanner(System.in);
			String string=scan.nextLine();
			//将用户输入的字符串进行切割，获取指令
			String[] str=string.split(" ");
			//使用for循环，用户可以不断查询文件
			if(string.endsWith(".doc")|string.endsWith(".txt")|string.endsWith(".java")|string.endsWith(".cpp")|string.endsWith(".html")) {
			for(int i=0;i<str.length;i++) {
				//-c查询字符数
				if(str[i].equals("-c")) {
					//根据用户输入的文件名进行路径重新构建
					String newpath=path+"/"+str[2];
					File newfile=new File(newpath);
					if(newfile.exists()) {
					int countChar=tool.countChar(newfile);
			System.out.println("该文件包含的字符数为："+countChar);
					}else {
						System.out.print("输入文件名不存在！");
					}
					//-w查询单词数
					}else if(str[i].equals("-w")) {
					String newpath=path+"/"+str[2];
					File newfile=new File(newpath);
					if(newfile.isFile()) {
					int countWords=tool.countWords(newfile);
			System.out.println("该文件包含的单词数为："+countWords);
					}else {
						System.out.print("该文件不存在！！");
					}
					//-l查询行数
				}else if(str[i].equals("-l")) {
					String newpath=path+"/"+str[2];
					File newfile=new File(newpath);
					if(newfile.isFile()) {
					int countLines=tool.countLines(newfile);
			System.out.println("该文件包含的行数为："+countLines);
					}
					else {
						System.out.print(newfile.getName());
						System.out.print("该文件不存在！！");
					}
					//-s进行通配符查询
				}else if(str[i].equals("-s")){
					//设置文件名读取
					String newstr=null;
					if(str[i+2].contains("*")|str[i+2].contains("$")) {
						newstr=str[i+2].substring(str[i+2].lastIndexOf("."), str[i+2].length());
						System.out.println("后缀为："+newstr);
					}else {
						newstr=str[i+2];
					}
					//使用文件夹的路径
					dir.dirTraverse(file,newstr,str[i+1]);
//					//-a查询特殊行
				}else if(str[i].equals("-a")){
					String newpath=path+"/"+str[2];
					File newfile=new File(newpath);
					if(newfile.isFile()) {
						int[] spestr=tool.specialLines(newfile);
						System.out.println("空行数为："+spestr[0]);
						System.out.println("代码行数为："+spestr[1]);
						System.out.println("注释行数为："+spestr[2]);
					}
					//-x显示图形界面
				}else if(str[i].equals("-x")){
					new wcGUI().GUI();
				}else if(str[i].equals("-o")) {
					System.out.print("退出系统！！");
					//退出系统，操作结束
					System.exit(0);
				}
					
				}
			}else {
				System.out.println("该文件不是文本文件！！请重新输入：");
			}
		}
	}else {
		System.out.println("文件路径错误!");
	}
}
}
	
