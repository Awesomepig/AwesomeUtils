/**
 * 
 */
package com.asomepig.tika;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.util.ImageIOUtil;

/**
 * @author eric
 *
 */
public class TestPdfbo {

	/**
	 * 
	 */
	public TestPdfbo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("STARTING....");
//		String filePath = "/home/eric/DevSpace/eclipse_project/TestRun/src/com/testRun/tika/";
//		String filePath = "/home/eric/DevSpace/eclipse_project/wsbsxt/src/blocks/app-share/src/main/webapp/resources/test/initTemplet/pdf/";
//		String fileName = "0030C017_V1/BJXKNY_BJSNZWPZYZSQS_V011.pdf";
		String rootPath = System.getProperty("user.dir");
		String filePath = rootPath+"\\pics\\";
		String fileName = "1.pdf";
		String fontPath = rootPath+"\\src\\com\\testRun\\tika\\";
		String fontName = "SimSun.ttf";
		//		String res = new TestPdfbo().getHTML(filePath, fileName);
//		System.out.println(res);
		int res = new TestPdfbo().convertPdf2Png(filePath, fileName, fontPath, fontName);
		System.out.println("CONVERT "+res+"PAGES!");
	}
	
	private int convertPdf2Png(String filePath,String fileName,String fontPath,String fontName){
		File f  = this.readFile(filePath, fileName);
		PDDocument document;
		int page = 0;
		try {
			document = PDDocument.loadNonSeq(f, null);
			System.out.println("GET THE PDF FILE!");
			
			PDDocumentInformation info = new PDDocumentInformation();
			document.setDocumentInformation(info);
			PDFont font = PDTrueTypeFont.loadTTF(document, new File( fontPath+fontName));
	        font.setWidths(PDType1Font.HELVETICA.getWidths());
			
			@SuppressWarnings("unchecked")
			List<PDPage> pdPages = document.getDocumentCatalog().getAllPages();
			System.out.println("GET PDF PAGES SUCCESSFUL!");
			for (PDPage pdPage : pdPages)
			{ 
				++page;
				BufferedImage bim = pdPage.convertToImage(BufferedImage.TYPE_BYTE_BINARY, 200);
				FileOutputStream op = new FileOutputStream(new File(filePath+"file_"+page+".png"));
				ImageIOUtil.writeImage(bim, "png",op,5);
			}
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return page;
	}

private File readFile(String path,String fileName){
		
		File res;
		res = new File(path+fileName);
		if(!res.exists())
			{
				System.err.println("cannot found files.. in com.testRun.tika.TestPdfbo.java line 35.");
				return null;
			}
		return res;
	}
	

}
