package com.shuixiaofei.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author xu
 *
 */
public class HttpCommConnectionUtil {
	/**
	 * 
	 * @param url 访问路径
	 * @param requestString  访问参数
	 * @param methodtype   访问方式
	 * @return
	 */
	public static String  httpUtils(String url,String requestString,String methodtype){
       //  String requestString = "我们要以流发送的数据...";
        HttpURLConnection httpURLConnection;
        try {
            //建立链接
            URL gatewayUrl = new URL(url);
            httpURLConnection = (HttpURLConnection) gatewayUrl.openConnection();
            
            httpURLConnection.setRequestMethod(methodtype);
            //设置连接属性
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);

            //获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
            byte[] requestStringBytes = requestString.getBytes(AppConstant.ENCODING_REQUEST);
            //设置请求属性
            httpURLConnection.setRequestProperty("Content-length", "" + requestStringBytes.length);
            httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
            //建立输出流，并写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(requestStringBytes);
            outputStream.close();
            //获得响应状态
            int responseCode = httpURLConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK == responseCode) {
                //当正确响应时处理数据
                System.out.println("Http Response OK...");
                StringBuffer responseBuffer = new StringBuffer();
                String readLine;
                BufferedReader responseReader;
                //处理响应流，必须与服务器响应流输出的编码一致
                responseReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), AppConstant.ENCODING_RESPONSE));
                while ((readLine = responseReader.readLine()) != null) {
                    responseBuffer.append(readLine).append("\n");
                }
                responseReader.close();
                //                System.out.println("Http Response:" + responseBuffer);
            	Map<String,String> map = new HashMap<String,String>();
            	map.put("responseCode", responseCode+"");
            	map.put("data", responseBuffer.toString());
            	map.put("code", "0000");
                return CommonUtils.serialize(map);
            }else{
            	Map<String,String> map = new HashMap<String,String>();
            	map.put("responseCode", responseCode+"");
            	map.put("message", "请求异常");
            	map.put("code", "1111");
            	return CommonUtils.serialize(map);
            }

        } catch (IOException e) {
            e.fillInStackTrace();
        }
    	Map<String,String> map = new HashMap<String,String>();
    	map.put("message", "请求异常");
    	map.put("code", "2222");
    	return CommonUtils.serialize(map);
	}
	
	public static void main(String[] args) {
		System.out.println(httpUtils("http://roger.shuixiaofei.com/asys/", "","POST"));
		
		
		
	}
	
	
	
	
}
