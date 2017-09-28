package com.shuixiaofei.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * 20170830
 * @author xu
 * 常用工具类
 */
public class CommonUtils {
	 /**
	  * 转换成json字符串
	  * @param object
	  * @return
	  */
	 public static <T> String serialize(T object) {
	    	//SerializerFeature.DisableCircularReferenceDetect; 解除引用"$ref": "$.
	          return JSON.toJSONString(object,SerializerFeature.DisableCircularReferenceDetect);
	      }
	 /**
	     * 获取单元格数据内容为字符串类型的数据
	     * 
	     * @param cell Excel单元格
	     * @return String 单元格数据内容
	     */
	    private String getStringCellValue(HSSFCell cell) {
	        String strCell = "";
	        switch (cell.getCellType()) {
	        case HSSFCell.CELL_TYPE_STRING:
	            strCell = cell.getStringCellValue();
	            break;
	        case HSSFCell.CELL_TYPE_NUMERIC:
	            strCell = String.valueOf(cell.getNumericCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BOOLEAN:
	            strCell = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BLANK:
	            strCell = "";
	            break;
	        default:
	            strCell = "";
	            break;
	        }
	        if (strCell.equals("") || strCell == null) {
	            return "";
	        }
	        return strCell;
	    }
	    /**
	     * 获取某年某月的天数
	     * @param time
	     * @return
	     */
	   public static int getSomeYearSomeMonthsDays(String time){
		    Calendar   cal   =   new   GregorianCalendar();  
		    SimpleDateFormat oSdf = new SimpleDateFormat ("",Locale.ENGLISH);  
		    oSdf.applyPattern("yyyy年MM月");  
		    try {  
		        cal.setTime(oSdf.parse(time));  
		    } catch (ParseException e) {  
		        e.printStackTrace();  
		    } 
		     return cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
		   
	   }
	    /***
	     * 获取随机的id
	     * @return
	     */
	    public static String getUUId(){
	    	UUID uuid = UUID.randomUUID(); 
			String id = uuid.toString().replace("-", ""); // 新增工单id
	    	return id;
	    }
	    
	   
	   
	   
	   
}
