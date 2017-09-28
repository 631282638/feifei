package com.shuixiaofei.utils;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;

public class PlanExcelHelper {
	public static Integer SHEET_MAX_ROW = 65536;
	/**
	 * <p>方法描述：TODO根据传入的中文名称列表、表字段名列表和数据集合导出EXCEL</p>
	 * @param nameList	中文名称（列头）
	 * @param cnList	表字段名,表字段名顺序
	 * @param dataList	数据集合，HashMap中key值要与表字段名大小写保持一致
	 * @return HSSFWorkbook	Excel表格对象
	 * @throws Exception 
	 */
	public static HSSFWorkbook doExportByPara(List<String> nameList,List<String> cnList,List<HashMap<String,String>> dataList,int size,String[] numsz,String[] namesz) throws Exception{
		if(nameList == null || nameList.size()<1){
			return doExportByPara(cnList,dataList);
		}
		if(cnList == null||cnList.size()<1){
			throw new Exception("表字段名列表为空！");
		}
		if(cnList.size() != nameList.size()){
			throw new Exception("表头中文名称列表与表字段名列表长度不一致！");
		}
		if(dataList == null || dataList.size()<1){
			throw new Exception("数据集合为空！");
		}
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		
		int sheetNums = 1;
		if(SHEET_MAX_ROW > 1){
			sheetNums = size;
		}
		int listPos = 0;
		for(int sheetNo = 0;sheetNo<sheetNums;sheetNo++){
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("标签"+sheetNo);			
			sheet.setColumnWidth(0, 1500);
			sheet.setColumnWidth(1, 4000);
			sheet.setColumnWidth(2, 4000);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 6000);
			sheet.setColumnWidth(5, 6000);
			sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 4000);
			sheet.setColumnWidth(8, 4000);
			sheet.setColumnWidth(9, 4000);
			sheet.setColumnWidth(10, 4000);
			sheet.setColumnWidth(11, 6000);
			sheet.setColumnWidth(12, 4000);
			sheet.setColumnWidth(13, 4000);
			sheet.setColumnWidth(14, 6000);
			
			// 设置字体    
			HSSFFont headfont = wb.createFont();    
			headfont.setFontName("黑体");    
			headfont.setFontHeightInPoints((short) 16);// 字体大小    
			headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗

			
			// 另一个样式    
			HSSFCellStyle headstyle = wb.createCellStyle();    
			headstyle.setFont(headfont);    
			headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中    
			headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中    
			headstyle.setLocked(true);    
			headstyle.setWrapText(true);// 自动换行
			headstyle.setTopBorderColor(HSSFColor.BLACK.index);// 上边框的颜色   
			headstyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色   
			headstyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色   
			headstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 下边框颜色
			headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框

			
			HSSFRow row0 = sheet.createRow(0);    
			// 设置行高    
			row0.setHeight((short) 600);    
			// 创建第一列    
			HSSFCell cell0 = row0.createCell(0);    
			cell0.setCellValue(new HSSFRichTextString(namesz[sheetNo]));    
			cell0.setCellStyle(headstyle);    
			/**  
			* 合并单元格  
			*    第一个参数：第一个单元格的行数（从0开始）  
			*    第二个参数：第二个单元格的行数（从0开始）  
			*    第三个参数：第一个单元格的列数（从0开始）  
			*    第四个参数：第二个单元格的列数（从0开始）  
			*/    
			CellRangeAddress range = new CellRangeAddress(0, 0, 0, 14);    
			sheet.addMergedRegion(range);    

			
			// 第三步，在sheet中添加表头第1行
			HSSFRow row = sheet.createRow((int) 1);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			HSSFCellStyle headStyle = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			style.setBorderRight(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			style.setWrapText(true);// 自动换行
			
			
			headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
			headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置单元格的背景颜色
			headStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);// 设置单元格的背景颜色
			headStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色．
			headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框
			headStyle.setWrapText(true);// 自动换行
			
			//创建字体 加粗 10号字
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			font.setFontHeightInPoints((short) 10);
			headStyle.setFont(font);
			
			//写入表头数据
			HSSFCell cell = null;
			for(int i = 0;i<nameList.size();i++){
				cell = row.createCell(i);
				row.setHeight((short) 800); 
				cell.setCellValue(nameList.get(i));
				cell.setCellStyle(headStyle);
			}

			// 第五步，写入实体数据 实际应用中这些数据从数据库得到
			//表数据左对齐
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			HashMap<String,String> map = null;
			int rowNum = 2;
			for(int i = listPos;i<Integer.parseInt(numsz[sheetNo]);i++){
				if(rowNum == (i)){
					listPos = i;
					break;
				}
				map = dataList.get(i);
				//表头已经占去2行
				row = sheet.createRow(rowNum);
				rowNum += 1;
				for(int j = 0;j<cnList.size();j++){
					if(AppConstant.EXCEL_EXPORT_SEQUENCE.equals(cnList.get(j))){
						cell = row.createCell(j);
						cell.setCellValue((i+1)+"");
						cell.setCellStyle(style);
					}else{
						cell = row.createCell(j);
						cell.setCellValue(map.get(cnList.get(j)));
						cell.setCellStyle(style);
					}
				}
			}
		}
		
		return wb;
	}
	
	/**
	 * <p>方法描述：TODO根据传入的表字段名列表和数据集合导出EXCEL</p>
	 * @param cnList	表字段名,表字段名顺序
	 * @param dataList	数据集合，HashMap中key值要与表字段名大小写保持一致
	 * @return HSSFWorkbook	Excel表格对象
	 * @throws Exception 
	 */
	public static HSSFWorkbook doExportByPara(List<String> cnList,List<HashMap<String,String>> dataList) throws Exception{
		if(cnList == null||cnList.size()<1){
			throw new Exception("表段名列表为空！");
		}
		if(dataList == null || dataList.size()<1){
			throw new Exception("数据集合为空！");
		}
		
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		
		int sheetNums = 1;
		if(SHEET_MAX_ROW > 1){
			sheetNums = dataList.size()/(SHEET_MAX_ROW)+1;
		}
		int listPos = 0;
		for(int sheetNo = 1;sheetNo<sheetNums+1;sheetNo++){
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("标签"+sheetNo);
			// 第三步，在sheet中添加表头第0行
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			
			// 第五步，写入实体数据 实际应用中这些数据从数据库得到
			//表数据左对齐
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			HashMap<String,String> map = null;
			int rowNum = 0;
			HSSFCell cell = null;
			for(int i = listPos;i<dataList.size();i++){
				if(rowNum == (SHEET_MAX_ROW)){
					listPos = i;
					break;
				}
				map = dataList.get(i);
				//表头已经占去1行
				row = sheet.createRow(rowNum);
				rowNum += 1;
				for(int j = 0;j<cnList.size();j++){
					if(AppConstant.EXCEL_EXPORT_SEQUENCE.equals(cnList.get(j))){
						cell = row.createCell(j);
						cell.setCellValue((i+1)+"");
						cell.setCellStyle(style);
					}else{
						cell = row.createCell(j);
						cell.setCellValue(map.get(cnList.get(j)));
						cell.setCellStyle(style);
					}
				}
			}
		}
		return wb;
	}

}
