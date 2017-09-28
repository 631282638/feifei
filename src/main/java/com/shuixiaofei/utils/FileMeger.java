package com.shuixiaofei.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class FileMeger {

	
	public static void main(String[] args)  throws Exception {
		String path = "F:/demo";
		File file = new File(path);
		HSSFWorkbook wb = new  HSSFWorkbook();
		
		
		for(String name :file.list()){
		    POIFSFileSystem fs= new POIFSFileSystem(new FileInputStream(path+"/"+name));   
		    //得到Excel工作簿对象    
		    HSSFWorkbook nwb = new HSSFWorkbook(fs);  
		    //得到Excel工作表对象    
		    HSSFSheet sheet = wb.getSheetAt(0);
		 //   int row = sheet.get
		  //  int colum = sheet.get
		    
		    //得到Excel工作表的行    
		 //   HSSFRow row = sheet.getRow(i);  
		    //得到Excel工作表指定行的单元格    
		 //   HSSFCell cell = row.getCell((short) j);  
		 //   HSSFCellStyle  cellStyle = cell.getCellStyle();//得到单元格样式  

			
		}
		
		
		
		
		//	.createWorkbook(new File(path+"/合并所得.xls"));
		
		FileOutputStream fileout = new FileOutputStream(path+"/合并所得.xlsx");      

	/*	WritableSheet wsheet = wb.createSheet("all", 0);
		String[] files = file.list();
		for(String name :files){
		    Workbook needToMegerExcel = Workbook.getWorkbook(new FileInputStream(path+"/"+name));	
		    Sheet sheet0 = needToMegerExcel.getSheet(0);
			//把原来的sheet0先写过去
		      for (int i = 0; i < sheet0.getRows(); i++) {
			        for (int j = 0; j < sheet0.getColumns(); j++) {
			            Label label = new Label(j, i, sheet0.getCell(j, i).getContents());
			            wsheet.addCell(label);
			        }
		      }
			
		}*/
		
		
		System.out.println(file.exists());
		System.out.println(file.list()[0]);
		
		

	     //   jxl.Workbook rw = jxl.Workbook.getWorkbook(new File("F:/7.25-7.31.xls"));
     //   jxl.Workbook rw = jxl.Workbook.getWorkbook(new File("7.25-7.31.xls"));

		 //创建可写入的Excel工作薄对象  
		// WritableWorkbook wwb=Workbook.createWorkbook(new File("F:/"+excleName+".xls"), rw);
   //    WritableWorkbook wwb=Workbook.createWorkbook(new File(excleName+".xls"), needToMegerExcel);
		 //读取第一张工作表  
		
		
		
		
		
		
	}

}
