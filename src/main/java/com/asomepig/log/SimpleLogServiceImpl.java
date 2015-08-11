package com.asomepig.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleLogServiceImpl implements SimpleLogService {

//	private File log;
//	private FileInputStream fin;
//	private FileOutputStream fout;
	private FileWriter fw;
	private SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSSS");
	
	
	
	public SimpleLogServiceImpl() {
		super();
		String path = System.getProperty("user.dir")+File.separator+"log";
		new File(path).mkdirs();
		File log = new File(path+"log.txt");
		try {
			if(!log.exists())log.createNewFile();
//			this.log = log;
//			this.fin = new FileInputStream(log);
//			this.fout = new FileOutputStream(log);
			this.fw = new FileWriter(log,true);//是否追加
		} catch (FileNotFoundException e) {
			System.err.println("找不到文件:"+log.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("文件读写错误:"+log.getAbsolutePath());
			e.printStackTrace();
		}
	}


	@Override
	public void append(String content){
		try {
			fw.write(format.format(new Date()+content+"\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
//			this.fin.close();
//			this.fout.close();
			this.fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		String rootPath = System.getProperty("user.dir");
//		String pdfPath = rootPath+File.separator+"pics\\2.pdf";
//		String path = rootPath+File.separator+"log";
//		File log = new File(path+File.separator+"log.txt");
//		log.mkdirs();
//	}
}









