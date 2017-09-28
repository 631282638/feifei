package com.shuixiaofei.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnectionUtil {
	  public static final String ENCODING_REQUEST = "utf-8";
	  public static final String ENCODING_RESPONSE = "utf-8";


	    public static void main(String args[]) {
	        String requestString = "我们要以流发送的数据...";
	        HttpURLConnection httpURLConnection;
	        try {
	            //建立链接
	            URL gatewayUrl = new URL("http://localhost/xmlTest.do");
	            httpURLConnection = (HttpURLConnection) gatewayUrl.openConnection();
	            
	            httpURLConnection.setRequestMethod("POST");
	            //设置连接属性
	            httpURLConnection.setDoOutput(true);
	            httpURLConnection.setDoInput(true);
	            httpURLConnection.setUseCaches(false);

	            //获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
	            byte[] requestStringBytes = requestString.getBytes(ENCODING_REQUEST);

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

	                System.out.println("");
	                System.out.println("Http Response OK...");
	                System.out.println("");

	                StringBuffer responseBuffer = new StringBuffer();

	                String readLine;
	                BufferedReader responseReader;
	                //处理响应流，必须与服务器响应流输出的编码一致
	                responseReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), ENCODING_RESPONSE));
	                while ((readLine = responseReader.readLine()) != null) {
	                    responseBuffer.append(readLine).append("\n");
	                }
	                responseReader.close();

	                System.out.println("Http Response:" + responseBuffer);
	            }

	        } catch (IOException e) {
	            e.fillInStackTrace();
	        }


	    }
}
