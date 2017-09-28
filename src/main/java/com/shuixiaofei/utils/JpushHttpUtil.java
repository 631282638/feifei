package com.shuixiaofei.utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


import cn.jiguang.common.utils.Base64;


public class JpushHttpUtil{
	 private static class TrustAnyTrustManager implements X509TrustManager {
	        
	        public void checkClientTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }
	 
	        public void checkServerTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }
	 
	        public X509Certificate[] getAcceptedIssuers() {
	            return new X509Certificate[] {};
	        }
	    }
	 
	    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    }



	/**
	 * 发送Https请求POST
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String sendPostHttps(String url, String param,String appkey,String masterkey)
			throws Exception {
		
		SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
                    new java.security.SecureRandom());
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		// 这里请填写自己的 appKey:masterSecret
		String userPassword = appkey+":"+masterkey;
		String encoding = new String(Base64.encode(userPassword.getBytes()));
		PrintWriter out = null;
		//添加请求头
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("charset", "UTF-8");
		con.setSSLSocketFactory(sc.getSocketFactory());
        con.setHostnameVerifier(new TrustAnyHostnameVerifier());		// 对 appKey 加上冒号，加上 masterSecret 拼装起来的字符串，再做 base64 转换。
		con.setRequestProperty("Authorization", "Basic " + encoding);
		// Send post request
		con.setDoOutput(true);
		con.setDoInput(true);
        byte[] bypes = param.toString().getBytes("UTF-8");
        con.getOutputStream().write(bypes);// 输入参数
		int responseCode = con.getResponseCode();
		// 这里可以加以判断responseCode是否==200
			System.out.println("发送Jpush推送Response--Code=" + responseCode);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}

}
