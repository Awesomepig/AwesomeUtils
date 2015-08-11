package com.asomepig.jcob;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RunJacob {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String wordFile = "C:\\workspace\\TestRun\\src\\com\\testRun\\jcob\\1.doc";
//		String imagePath1 = "C:\\workspace\\TestRun\\src\\com\\testRun\\jcob\\1.jpg";
//		String imagePath2 = "C:\\workspace\\TestRun\\src\\com\\testRun\\jcob\\2.jpg";
//		String tarStr1 = "Pic1";
//		String tarStr2 = "Pic2";
		try {
			String path = System.getProperty("java.class.path");
			String path2 = System.getProperty("user.dir");
			String osType = System.getProperty("os.name").toLowerCase();
			System.out.println("我们获取到的---[java.class.path]---: "+path);
			System.out.println("我们获取到的---[user.dir]---: "+path2);
			System.out.println("我们获取到的---[系统类型]---: "+osType);
			// ------------------------------------------------- 系统命令拷贝文件 ------------------------------------------------
			try {
				if(osType.indexOf("windows")==-1) throw new Exception();
				//Linux系统命令：ls －l  
				String command = "copy oragin\\1.doc ";  
				//获取当前系统的环境。  
				Runtime rt = Runtime.getRuntime();  
				//执行  
				Process p = null;  
				p = rt.exec(command);  
				//获取执行后的数据  
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
				String msg = null;  
				//输出。  
				while((msg = br.readLine())!=null){  
					System.out.println(msg);  
				}  
				br.close();  
				
			} catch (Exception e) {
				System.out.println("当前操作系统不是windows,不满足操作要求!");
			}
			// ------------------------------------------------- 系统命令拷贝文件 ------------------------------------------------
			Scanner sc = new Scanner(System.in);
			boolean hehe = true;
			while(hehe)
			{
				System.out.print("请确定是否停止监听用户输入 (y/n): ");
				String res = sc.next().toLowerCase();
				if(res.equals("y"))
				{
					hehe = false;
					System.out.println("\n 停止监听,5秒钟后当前窗口将自动关闭!");
					for (int i = 5; i > 0; i--) {
						Thread.sleep(1000);
						System.out.println(i);
					}
				}
			}
//			JobUtil ju =  new JobUtil();
//			ju.insertImage(wordFile, imagePath, tarStr);
//			ju.intoValueBookMark("bm1", "who knows !");
			
			
			// ------------------------------ 指定书签处插入图片
//			TestWordInsert poi = new TestWordInsert(wordFile);
//			poi.addImageAtBookMark(tarStr2, imagePath2);
//			System.out.println("图片2成功！");
//			poi.addImageAtBookMark(tarStr1, imagePath1);
//			System.out.println("图片1成功！");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("设置图片出错！！！");
		}
		System.out.println("设置完成！");
	}

}
