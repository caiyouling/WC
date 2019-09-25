package WC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.filechooser.FileFilter;

public class Count {
	public int countChar(File file) {
		//使用Reader字符流进行读取数据
		Reader read = null;
		int len=0,countchar=0,sum=0;
		try {
			read = new FileReader(file);
			//开辟长度为1的字符数组
			char[] flush=new char[1];
			//利用read()方法读取字符
			while(-1!=(len=read.read(flush))) {
				countchar++;
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(read!=null) {
				try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("文件关闭失败");
				}
			}
		}
		return countchar;
	
		
	}
	public int countWords(File file) {
		Reader read = null;
		//len为每次实际读取的个数
		int len=0,countwords=0,sum=0;
		try {
			read = new FileReader(file);
			char[] flush=new char[1024];
			while(-1!=(len=read.read(flush))) {
			String str=new String(flush,0,len);
			// 使用正则表达式进行分割，遇到{}或()或.空格则进行分割
			String[] string=str.split("\\{|\\}|\\ |\\.|\\(|\\)");//还有其他符号
			countwords=string.length;
			sum=sum+countwords;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(read!=null) {
				try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("文件关闭失败");
				}
			}
		}
		return sum;
	}
	
	public int countLines(File file) {
		Reader read = null;
		int len=0,countlines=0,sum=0;
		try {
			read = new FileReader(file);
			char[] flush=new char[1024];
			while(-1!=(len=read.read(flush))) {
				//将字符数组转为字符串
					String str=new String(flush,0,len);
					//根据换行符进行判断行数多少
					String[] string=str.split("\r");
					countlines=string.length;
					sum=sum+countlines;
				}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(read!=null) {
				try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("文件关闭失败");
				}
			}
		}
			return countlines;
		}
	//特殊的代码行，空行，以及注释行
	public int[] specialLines(File file) {
		Reader read=null;
		 int len=0, nullLine=0,wordLine=0;
		int noteLine=0;
		try {
			read=new FileReader(file);
			char[] flush=new char[1024];
			//用来标志注释行
			
			while(-1!=(len=read.read(flush))) {
				String str=new String(flush,0,len);
				//根据行数进行分割
				String[] str1=str.split("\\n");
				
				//怎么匹配空行呢？去掉首尾空格，匹配控制元素，以及中括号
				for(int i=0;i<str1.length;i++) {
					
					//判断是否为空行，空白字符中存在{}也是空行
				if(str1[i].trim().matches("^\\s*|[{}]*$")) {
					nullLine++;
					
					//代码行多于一个字符且不能为注释的一半
				}else if(str1[i].trim().matches("^[\\<\\/]*[\\{]*[a-zA-Z0-9]+.*$")){
					wordLine++;
					
				}else {
					if(str1[i].trim().contains("\\")||(str1[i].trim().contains("/*")&&str1[i].trim().contains("*/"))){
						noteLine++;
					}
					//觉得注释行情况太多，于是直接弄成else
				}
				}
		}
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("文件不存在！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("文件读取失败！");
		}finally {
			if(read!=null) {
				try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("文件关闭失败！");
				}
			}
		}
		//使用整型数组进行存放数据
		int[] spestr=new int[3];
				spestr[0]=nullLine;
				spestr[1]=wordLine;
				spestr[2]=noteLine;
		return spestr;
	}

}
