/**
 * 
 */
package com.asomepig.jxl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *  根据excel一列的值,找到pdf文件夹下对应的文件夹
 *  并把对应的文件夹下所有pdf文件名拼接后,放到另外一列上
 * @author eric
 *
 */
public class CircleExcel {

	/**
	 * 
	 */
	public String exPath;
	public String pdfPath;
	public CircleExcel(String path,String pdfp) {
		this.exPath = path;
		this.pdfPath = pdfp;
	}

	public static void main(String[] args) {
		CircleExcel ce = new CircleExcel("/home/eric/temp/test/1.xls", "/home/eric/temp/test/pdf");
		ce.doIt();
	}
	
	
	
	public boolean doIt()
	{

        try {
        	// 1.获取excel文件夹
        	File pp = new File(pdfPath);
        	ArrayList<String> dirList = new ArrayList<String>();
        	for(String name:pp.list())
        		dirList.add(name);
        	
			Workbook book = Workbook.getWorkbook(new File(exPath));
			WritableWorkbook bookT = Workbook.createWorkbook(new File(exPath),  
					book);
//			Sheet sheet = book.getSheet(0);
			WritableSheet sheetT = bookT.getSheet(0);
			int tableNameX = 2;
			int pdfNameX = 3;
			
			for (int j = 1; j < sheetT.getRows(); j++) 
			{
				int nameY = j;
				String pdfs = "";
				String tableName = sheetT.getCell(tableNameX, nameY).getContents();
				if(tableName.equals("")||!dirList.contains(tableName.substring(tableName.indexOf("_00")+1)))
				{
					pdfs = "空";
				}else{
					File pdfDirFile = new File(pdfPath+"/"+tableName.substring(tableName.indexOf("_00")+1));// 其实substring用index13也行
					if(pdfDirFile.list().length>0)
					{
						for(String fn:pdfDirFile.list())pdfs+=fn.substring(0,fn.indexOf(".pdf"))+";";
					}else
					{
						pdfs = "空";
					}
				}
//				WritableCell pdfcell = sheetT.getWritableCell(pdfNameX, nameY);
//					Label  labelCellT= new Label(pdfNameX, nameY, pdfs); 
//					sheetT.addCell(labelCellT);
				WritableCell cell =sheetT.getWritableCell(pdfNameX, nameY);
				System.out.println(cell.getType());
				jxl.format.CellFormat cf = cell.getCellFormat();//获取单元格的格式
				jxl.write.Label lbl = new jxl.write.Label(pdfNameX, nameY, pdfs);
				lbl.setCellFormat(cf);
				sheetT.addCell(lbl);
				
				
			}
			
			// 输出变更内容
			bookT.write();
			bookT.close();
			
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
}
