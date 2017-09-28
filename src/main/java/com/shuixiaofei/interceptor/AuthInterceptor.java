package com.shuixiaofei.interceptor;

import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sun.tools.internal.ws.processor.model.Request;
/**
 * 20170823
 * @author xu
 * for validate auth 
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
	public static final String SESSION_USERID = "kUSERID";  
    public static final String SESSION_AUTHS = "kAUTHS";  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
        boolean flag = true;  
        if (handler instanceof HandlerMethod) {  
            Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);  
            if (auth != null) {// 有权限控制的就要检查  
                if (request.getSession().getAttribute(SESSION_USERID) == null) {// 没登录就要求登录  
                    response.setStatus(HttpStatus.FORBIDDEN.value());  
                    response.setContentType("text/html; charset=UTF-8");  
                    
                    if(request.getHeader("x-requested-with") != null   
                            && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){   
                    // Ajax请求, 前段根据此header进行处理  
                        response.setHeader("sessionstatus", "timeout");
                    // 返回未认证的状态码(401)  
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());  
                    //    logger.debug("请求路径：" + uri + ", 请求方式 ：Ajax请求, Session超时, 需要重新登录!");  
                    }else{
                        String str = "<script language='javascript'>alert('会话过期,请重新登录');"
                        + "window.top.location.href='"
                        + request.getContextPath()
                        + "';</script>";
                        response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
                        PrintWriter writer = response.getWriter();
                        writer.write(str);
                        writer.flush();
                        writer.close();
                    	
                    }
                    flag = false;  
                 //     request.getRequestDispatcher("/index.jsp").forward(request, response);  
                    
                } else {// 登录了检查,方法上只是@Auth,表示只要求登录就能通过.@Auth("authority")这类型,验证用户权限  
                    if (!"".equals(auth.value())) {  
                        Set<String> auths = (Set<String>) request.getSession().getAttribute(SESSION_AUTHS);  
                        if (!auths.contains(auth.value())) {// 提示用户没权限  
                            response.setStatus(HttpStatus.FORBIDDEN.value());  
                            response.setContentType("text/html; charset=UTF-8");  
                            PrintWriter out=response.getWriter();  
                            out.write("{\"type\":\"noauth\",\"msg\":\"您没有"+auth.name()+"权限!\"}");  
                            out.flush();  
                            out.close();  
                            flag = false;  
                        }  
                    }  
                }  
            }  
        }  
        return flag;  
    }  
}
