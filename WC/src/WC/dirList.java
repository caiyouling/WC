package WC;

import java.io.File;
import java.io.FilenameFilter;

public class dirList {
	public void dirTraverse(File file,String docstr,String str) {
		//遍历目录下所有符合条件的文件，输出各个文件的行数，单词数，字符数
		Count count=new Count();
		//找到newstr的父文件夹，然后进行筛选
		if(file.isDirectory()) {
			File[] files=file.listFiles();

			//创建一个放入筛选好的文件数组
			File[] newfile=new File[files.length];
			int arr=0;
			for(int i=0;i<files.length;i++) {
				//根据文件的后缀名进行筛选
				if(files[i].isFile()&&files[i].getName().endsWith(docstr)) {
					newfile[arr]=files[i];
					arr++;
					//若遇到文件夹，则进行递归查询
				}else if(files[i].isDirectory()) {
					dirTraverse(files[i],docstr,str);
				}
			}
			//递归显示文件数据信息
			for(int i=0;i<arr;i++) {
				//根据用户输入的指令选择功能
				if(newfile[i].isFile()) {
					//每次循环只进行一条指令，使用flag进行标记
					boolean flag=true;
					System.out.println("文件名："+newfile[i].getName());
				while(flag) {
					
					switch(str) {
					case "-c":{
						System.out.println("字符数："+count.countChar(newfile[i]));flag=false;break;
					}
					case "-w":{
						System.out.println("单词数："+count.countWords(newfile[i]));flag=false;break;
					}
					case "-l":{
						System.out.println("行数："+count.countLines(newfile[i]));flag=false;break;
					}
					case "-a":{
						int[] spestr=count.specialLines(newfile[i]);
						System.out.println("空行数为："+spestr[0]);
						System.out.println("代码行数为："+spestr[1]);
						System.out.println("注释行数为："+spestr[2]);
						flag=false;break;
					}
					default:System.out.print("输入指令出错！！");flag=false;break;
					}
				}
				//获取文件名
				}else if(files[i].isDirectory()) {
					//若是文件夹，则进行递归筛选
					dirTraverse(files[i],docstr,str);
				}
			}
		}else {
			System.out.print("没有判断出文件信息");
		}
	}
}
