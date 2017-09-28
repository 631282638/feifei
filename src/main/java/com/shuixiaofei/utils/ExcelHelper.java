package  com.shuixiaofei.utils;

import java.util.HashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelHelper {
	public static Integer SHEET_MAX_ROW = 65536;
	/**
	 * <p>方法描述：TODO根据传入的中文名称列表、表字段名列表和数据集合导出EXCEL</p>
	 * @param nameList	中文名称（列头）
	 * @param cnList	表字段名,表字段名顺序
	 * @param dataList	数据集合，HashMap中key值要与表字段名大小写保持一致
	 * @return HSSFWorkbook	Excel表格对象
	 * @throws Exception 
	 */
	public static HSSFWorkbook doExportByPara(List<String> nameList,List<String> cnList,List<HashMap<String,Object>> dataList) throws Exception{
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
			sheetNums = dataList.size()/(SHEET_MAX_ROW-1)+1;
		}
		int listPos = 0;
		for(int sheetNo = 1;sheetNo<sheetNums+1;sheetNo++){
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("标签"+sheetNo);
			// 第三步，在sheet中添加表头第0行
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头 设置表头居中
			HSSFCellStyle style = wb.createCellStyle();
			HSSFCellStyle headStyle = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			//设置自动换行 add by sushi
			style.setWrapText(true);
			headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
			//创建字体 加粗 20号字
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			//font.setFontHeightInPoints((short)20);
			headStyle.setFont(font);
			
			//设置列宽 add by sushi
			for(int i = 0;i<nameList.size();i++){
				String str = nameList.get(i);
				int length = str.getBytes().length;
				sheet.setColumnWidth(i,length*350);
			}
			//写入表头数据
			HSSFCell cell = null;
			for(int i = 0;i<nameList.size();i++){
				cell = row.createCell(i);
				cell.setCellValue(nameList.get(i));
				cell.setCellStyle(headStyle);
			}

			// 第五步，写入实体数据 实际应用中这些数据从数据库得到
			//表数据左对齐
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			HashMap<String,Object> map = null;
			int rowNum = 1;
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
						String keyt = cnList.get(j).toString();
			/*			System.out.println(keyt);
						System.out.println(CommonUtils.serialize(map));*/
						String tempMa = map.get(keyt).toString();
						cell.setCellValue(tempMa);
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
	public static HSSFWorkbook doExportByPara(List<String> cnList,List<HashMap<String,Object>> dataList) throws Exception{
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
			HashMap<String,Object> map = null;
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
						cell.setCellValue(map.get(cnList.get(j)).toString());
						cell.setCellStyle(style);
					}
				}
			}
		}
		return wb;
	}
	
	/**
	 * <p>方法描述：TODO根据传入的中文名称列表、表字段名列表和数据集合导出EXCEL</p>
	 * @param nameList	中文名称（列头）
	 * @param cnList	表字段名,表字段名顺序
	 * @param dataList	数据集合，HashMap中key值要与表字段名大小写保持一致
	 * @return HSSFWorkbook	Excel表格对象
	 * @throws Exception 
	 */
	public static HSSFWorkbook doExportByPara(HSSFWorkbook wb, List<String> nameList,List<String> cnList,List<HashMap<String,Object>> dataList, int sheetIndex) throws Exception{
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
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.getSheetAt(sheetIndex);
		//HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行
		HSSFRow row = sheet.getRow(0);
		if(row == null) {
			row = sheet.createRow(0);
		}
		//HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		HSSFCellStyle headStyle = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		//创建字体 加粗 20号字
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short)12);
		headStyle.setFont(font);
		
		//写入表头数据
		HSSFCell cell = null;
		for(int i = 0;i<nameList.size();i++){
			cell = row.getCell(i);
			if(cell == null) {
				cell = row.createCell(i);
			}
			//cell = row.createCell(i);
			cell.setCellValue(nameList.get(i));
			cell.setCellStyle(headStyle);
		}

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到
		//表数据左对齐
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		HashMap<String,Object> map = null;
		int rowNum = 1;
		for(int i = 0;i<dataList.size();i++){
			map = dataList.get(i);
			//表头已经占去1行
			row = sheet.getRow(rowNum);
			if(row == null) {
				row = sheet.createRow(rowNum);
			}
			//row = sheet.createRow(rowNum);
			rowNum += 1;
			for(int j = 0;j<cnList.size();j++){
				if(AppConstant.EXCEL_EXPORT_SEQUENCE.equals(cnList.get(j))){
					cell = row.getCell(j);
					if(cell == null) {
						cell = row.createCell(j);
					}
					//cell = row.createCell(j);
					cell.setCellValue((i+1)+"");
					cell.setCellStyle(style);
				}else{
					cell = row.getCell(j);
					if(cell == null) {
						cell = row.createCell(j);
					}
					//cell = row.createCell(j);
					cell.setCellValue(map.get(cnList.get(j)).toString());
					cell.setCellStyle(style);
				}
			}
		}
		
		return wb;
	}
}