package com.shuixiaofei.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shuixiaofei.entity.QueryModel;
import com.shuixiaofei.interceptor.Auth;
import com.shuixiaofei.utils.AppConstant;
import com.shuixiaofei.utils.CommonUtils;
import com.shuixiaofei.utils.ExcelHelper;

/**
 * 处理表格 
 * @author xu
 *
 */
@Controller
@RequestMapping("merger")
public class DemoCtrl {
	 /** 日志信息   **/
	final Logger logger = Logger.getLogger(DemoCtrl.class);

	/**
	 * 跳转到表格处理页面
	 * @return
	 */
	@Auth("All")
	@RequestMapping(value="/excel.do",method = RequestMethod.GET)
	public String showExcelPages(){
		return "pages/excel/index";
	}
	@Auth("All")
	@RequestMapping(value = "/dataexcel.do", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request, HttpServletResponse response){			
		try{
			PrintWriter printWriter = response.getWriter();	
			Map<String,Object> map = new HashMap<String,Object>();
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
	                    Workbook wb=null;
	                    if(file.getOriginalFilename().endsWith(".xls")){
		                    wb= new HSSFWorkbook(file.getInputStream()); //处理以.xls结尾的excel

	                    } else if(file.getOriginalFilename().endsWith(".xlsx")) {
		                    wb= new XSSFWorkbook(file.getInputStream());//处理以.xlsx结尾的excel
	                    }else{
	                      	map.put("code", "0");
	                    	map.put("msg", "不是excel表格");
	                    	map.put("data", null);
	            			printWriter.print(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
	            			printWriter.flush();
	            			printWriter.close();	
	            			return;
	                    }	 
	                    Sheet sheet =wb.getSheetAt(0);//获取第一个sheet
	                    //得到有效的行数
	                    int rowcount = sheet.getLastRowNum();     
	                    // i=0 j=0 value=编号：储003
	                    String bianhao = null;
	                    // i=1 j=0 value=中国邮政储蓄存款余额分种类统计表（日报）
	                    //i=3 j=0 value=制表单位：临沂市分行--邮政代理网点
	                    String maker = null;
	                    // i=3 j=7 value=2017年07月31日
	                    String make_date = null;
	                    // i=3 j=22 value=单位：亿元、万元、%
	                    String unit = null;
	                    if(!sheet.getRow(0).getCell(0).toString().startsWith("编号")){
	                      	map.put("code", "0");
	                    	map.put("msg", "表格格式不正确");
	                    	map.put("data", null);
	            			printWriter.print(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
	            			printWriter.flush();
	            			printWriter.close();	
	            			return;
	                    }
	                    int backNum = 0 ;
	                    for(int i=0;i<rowcount;i++){
	                        //取得一行有效的单元格数
		                    Row row =sheet.getRow(i);
		                    int a =  row.getLastCellNum();     
		               //     System.out.println("第 "+i+" 行  的有效单元格数 "+a);

		                    
	                    }
	              //      System.out.println("backNum="+backNum);
	                    if(backNum==1){
	                    	map.put("code", "0");
	                    	map.put("msg", "导入成功");
	                    	map.put("data", null);
	            			printWriter.print(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
	            			printWriter.flush();
	            			printWriter.close();	
	            			return;
	                    }else{
	                    	map.put("code", "0");
	                    	map.put("msg", "失败");
	                    	map.put("data", null);
	            			printWriter.print(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
	            			printWriter.flush();
	            			printWriter.close();	
	            			return;
	                    }
	                       
	                    
	            /*        int a  = sheet.getRow(0).getRowNum();
						if (file.getOriginalFilename().contains(".")) {
							String fileName = file.getOriginalFilename();
							System.out.println(fileName);
					
							
						}*/
					}
				}
			}
	
		}catch (Exception e) {
		//	e.printStackTrace();
		}
	
	}

	/**
	 * 打印机构月度金融报表
	 * @param queryModel
	 * @param page
	 * @param limit
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Auth("All")
	@RequestMapping(value="/dayreport/export.do",method=RequestMethod.GET)
	public void exportMonthReportExcel(QueryModel queryModel,String page,String limit,HttpServletRequest request ,HttpServletResponse response)throws Exception{
		List<String> nameList = new ArrayList<String>();
		nameList.add("机构名称");
		nameList.add("支局名称");
		nameList.add("老机构号");
		nameList.add("新机构号");
		nameList.add("定期");
		nameList.add("活期");
		nameList.add("总奖励");
		List<String>  cnList = new ArrayList<String>(); 
		cnList.add("part_name");
		cnList.add("organization_name");
		cnList.add("old_organization_num");
		cnList.add("new_organization_num");
		cnList.add("ding_qi_yu_e");
		cnList.add("hqcxckye");
		cnList.add("zhi_ju_zong_jing_li");
		List<HashMap<String,Object>> dataList  = new ArrayList<HashMap<String,Object>>();
		String time = queryModel.getParam2().toString(); // 时间格式 2017-07
		String[]  timeArr = time.split("-");
		String time1 = timeArr[0]+"年"+timeArr[1]+"月";
		int month2 = Integer.parseInt(timeArr[1])-1;
		String time2= "";
		if(month2<10){
			 time2 = timeArr[0]+"年0"+month2+"月";
		}else{
			 time2 = timeArr[0]+"年"+month2+"月";
		}
		time1 = time1 + CommonUtils.getSomeYearSomeMonthsDays(time1)+"日";
		time2 = time2 +CommonUtils.getSomeYearSomeMonthsDays(time2)+"日";
		queryModel.setParam1(time2);
		queryModel.setParam2(time1);
		this.logger.info(CommonUtils.serialize(queryModel));
		// dataList = this.financialDayReportService.getFinancialMonthReportTotal(queryModel);
		//time+"各支局总奖励统计.xls"	
		HSSFWorkbook wb =  ExcelHelper.doExportByPara( nameList, cnList, dataList);
  
    	String cn = time+"各支局总奖励统计";
		// 设置文件下载mime类型
		response.reset();
	    response.setContentType("application/x-msdownload"); 
	    // 设置响应头信息
	    response.setHeader("Content-disposition", "attachment; filename="+URLEncoder.encode(cn,"UTF-8")+".xls");
		OutputStream os = response.getOutputStream();
		//调用EXCEL帮助类
		wb.write(os);
		os.flush();
		os.close();   
	}

	/**
	 * 根据查询条件统计金融月报数据
	 * @param queryModel
	 * 参数说明，第一个参数是年月 param1 默认是空，需要计算调用
	 * 第二个参数是  parma2 所要查询的
	 * 第三个参数 param3 机构名称
	 * 第四个参数 param4 支局名称
	 * 第五个参数param5老机构号
	 * 第六个参数param6新机构号
	 * 第七个参数 param7 页码
	 * 第八个参数param8每页多少条
	 * @param request
	 * @param response
	 * 
	 */
	@Auth("All")
	@RequestMapping(value="/query/month/data.do",method=RequestMethod.POST)
	public void getMonthReport(QueryModel queryModel,String page,String limit,HttpServletRequest request ,HttpServletResponse response) throws Exception{
	//	this.logger.info("查询某个月月报数据");
		int pageIndex = AppConstant.pageIndex;
		if(StringUtils.isNotBlank(page)){
			pageIndex = Integer.parseInt(page);
		}
		int pageSize = AppConstant.pageSize;
		if(StringUtils.isNotBlank(limit)){
			pageSize = Integer.parseInt(limit);
		}
		String time = queryModel.getParam2().toString(); // 时间格式 2017-07
		String[]  timeArr = time.split("-");
		String time1 = timeArr[0]+"年"+timeArr[1]+"月";
		int month2 = Integer.parseInt(timeArr[1])-1;
		String time2= "";
		if(month2<10){
			 time2 = timeArr[0]+"年0"+month2+"月";
		}else{
			 time2 = timeArr[0]+"年"+month2+"月";
		}
		time1 = time1 + CommonUtils.getSomeYearSomeMonthsDays(time1)+"日";
		time2 = time2 +CommonUtils.getSomeYearSomeMonthsDays(time2)+"日";
		queryModel.setParam1(time2);
		queryModel.setParam2(time1);
		this.logger.info(CommonUtils.serialize(queryModel));
		System.out.println(pageIndex);
		System.out.println( pageSize);
	//	PageList<?> pageData = this.financialDayReportService.getFinancialMonthReport(queryModel, pageIndex, pageSize);
		Map<String,Object> map = new HashMap<String, Object>();
	//	map.put("data", pageData);
		map.put("code", "0");
		map.put("msg", "s");
	//	map.put("count",pageData.getPaginator().getTotalCount());

		PrintWriter printWriter = response.getWriter();
		printWriter.print(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
		printWriter.flush();
		printWriter.close();	
//		map.put("pageInfo", pageData.getPaginator());             
		//	System.out.println(CommonUtils.serialize(map));
		/*	"pageInfo": {
		        "endRow": 30,
		        "firstPage": true,
		        "hasNextPage": true,
		        "hasPrePage": false,
		        "lastPage": false,
		        "limit": 30,
		        "nextPage": 2,
		        "offset": 0,
		        "page": 1,
		        "prePage": 1,
		        "slider": [1, 2],
		        "startRow": 1,
		        "totalCount": 60,
		        "totalPages": 2
		    }
			*/
	};
	
	
	
	
	
	
}























