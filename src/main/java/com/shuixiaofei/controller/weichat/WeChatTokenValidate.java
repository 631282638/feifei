package com.shuixiaofei.controller.weichat;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shuixiaofei.utils.AppConstant;
import com.shuixiaofei.wechat.utils.SHA1;

/**
 * 20170914 
 * 用于验证微信token接入
 * @author xu
 *
 */
@Controller
@RequestMapping("wechat")
public class WeChatTokenValidate {
	/**
	 * 
	 * @param signature 时间戳
	 * @param echostr 随机字符串
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/token/validate.do",method = RequestMethod.GET)
	public void validateWeChatToken(String signature,String echostr,String timestamp,String nonce,  HttpServletRequest request,HttpServletResponse response) throws Exception{
        String[] str = { AppConstant.TOKEN, timestamp, nonce };  
        // 字典序排序  
        Arrays.sort(str);   
        String bigStr = str[0] + str[1] + str[2];  
        // SHA1加密  
        String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();  
        // 确认请求来至微信  
        if (digest.equals(signature)) {  
            response.getWriter().print(echostr);  
        }  
		
	}
	
	
	
	

		
}
	
	
	
	

