package com.shuixiaofei.utils;

public class AppConstant {
	
	//EXCEL导出使用，标识导出的EXCEL的序号列
	public static String EXCEL_EXPORT_SEQUENCE = "sequence";
	// 请求编码方式
	public static final String ENCODING_REQUEST = "utf-8";
	// 返回编码方式
    public static final String ENCODING_RESPONSE = "utf-8";
	//秘钥
    public static final String desKey = "efEyKpHBs+q2OzcmXbX3QM1JQ/L0SQeF";
    // 默认页码
    public static final int pageIndex = 0;
    // 默认每页的数量 默认 10条每页
    public static final int pageSize = 10;
    // 用于验证微信接入的token
    public static final String TOKEN = "shuixiaofei";
    // 微信 appid
	public static final String appid = "wxb243df7cdbcad42f";
	// wechat secret
	public static final String appsecret = "c1fd1e8f476e4ce4c41de770cd75c325";

	/**
	 * 判断当前操作系统是不是window
	 * 
	 * @return boolean
	 */
	public static boolean isWindows() {
		boolean flag = false;
		if (System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
			flag = true;
		}
		return flag;
	}
	
}
