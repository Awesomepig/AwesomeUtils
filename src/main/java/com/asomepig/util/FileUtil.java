package com.asomepig.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileUtil {

	public static void copyFile(String iFilePath,String oFilePath){
		// 预处理，删除已经存在的out文件
		File oF = new File(oFilePath);
		if(oF.exists()){
			System.out.println("输出文件夹已存在word文件，现在我们要删除他。");
			oF.delete();
			System.out.println("输出文件夹文件删除成功。");
		}
		
		FileInputStream fi = null;

        FileOutputStream fo = null;

        FileChannel in = null;

        FileChannel out = null;
        try {

            fi = new FileInputStream(iFilePath);

            fo = new FileOutputStream(oFilePath);

            in = fi.getChannel();//得到对应的文件通道

            out = fo.getChannel();//得到对应的文件通道

            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                fi.close();

                in.close();

                fo.close();

                out.close();

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
	}
	
	public static void deleteFile(String filePath) throws FileNotFoundException{
		File f = new File(filePath);
		if(f.delete())
		{
			System.out.println("文件删除成功!"+filePath);
		}else
		{
			System.err.println("文件删除失败!"+filePath);
		}
	}
	
	public static void createDir(String path){
		File f = new File(path);
		if (f.exists())
			return;
		f.mkdirs();
	}
	
	/**
	 * 追加文件：使用FileOutputStream，在构造FileOutputStream时，把第二个参数设为true
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void method1(String file, String conent) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, true)));
			out.write(conent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 追加文件：使用FileWriter
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void method2(String fileName, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 追加文件：使用RandomAccessFile
	 * 
	 * @param fileName
	 *            文件名
	 * @param content
	 *            追加的内容
	 */
	public static void method3(String fileName, String content) {
		try {
			// 打开一个随机访问文件流，按读写方式
			RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			String sp = File.separator;
			String res = System.getProperty("user.dir")+sp+"res";
			File f = new File(res+sp+"1.txt");
			FileInputStream fin = new FileInputStream(f);
			InputStreamReader reader = new InputStreamReader(fin,"UTF8");
			BufferedReader br = new BufferedReader(reader);
			String tx = null;
			int i = 1;
			while ((tx = br.readLine()) != null) {
				System.out.println("第"+i+"行");
				System.out.println(tx);
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
