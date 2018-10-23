package com.mall.ssm.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mall.ssm.po.custom.UserCustom;

public class LoginIntercepter implements HandlerInterceptor {

	//进入 Handler方法之前执行
	//用于身份认证、身份授权
	//比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if(request.getParameter("xcx_access")!=null&&request.getParameter("xcx_access").trim().length()>0){
			return true;
		}
		
		
		/*
		 * 1资源文件无需过滤
		 */
		StringBuffer server = request.getRequestURL();
		//System.out.println(server.toString());
		if(server.toString().endsWith(".css") || 
				server.toString().contains("register") || 
				server.toString().endsWith(".js") || 
				server.toString().endsWith(".jpg") || 
				server.toString().endsWith(".jpeg") || 
				server.toString().endsWith(".gif") || 
				server.toString().endsWith(".ico") || 
				server.toString().endsWith(".png")){
			//如果发现是css或者js文件，直接放行
			//System.out.println("资源文件放行");
            return true;
        }
		
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpSession session = servletRequest.getSession();
		
		 
		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		
		// 从session里取员工工号信息
		//String empId = (String) session.getAttribute("empId");
		UserCustom user = (UserCustom) session.getAttribute("user");
		/*
		 * 创建类Constants.java，里面写的是无需过滤的页面 for (int i = 0; i <
		 * Constants.NoFilter_Pages.length; i++) { if
		 * (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
		 * chain.doFilter(servletRequest, servletResponse); return; } }
		 */
		 
		// 2判断如果没有取到员工信息,就跳转到登陆页面
		if (user == null) {
			//System.out.println("未登录");
			response.setHeader( "Pragma", "no-cache" );   
			response.setDateHeader("Expires", 0);   
			response.addHeader( "Cache-Control", "no-cache" );//浏览器和缓存服务器都不应该缓存页面信息
			response.addHeader( "Cache-Control", "no-store" );//请求和响应的信息都不应该被存储在对方的磁盘系统中；    
			response.addHeader( "Cache-Control", "must-revalidate" );///于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；

			if(servletRequest.getRequestURL().toString().endsWith("/index")){
				//(0访问index主页面但是session为空,重定向login)
				response.sendRedirect(servletRequest.getContextPath() + "/login");
			}else{
				if(servletRequest.getRequestURL().toString().endsWith("/login")){
					return true;
				}
				//(1访问非index页面但是session为空,跳转login)
				response.sendRedirect(servletRequest.getContextPath() + "/login");
			}
		} else{
			/*
			 * 登陆页面无需过滤
			 */ 
			if (server.indexOf("/login") > -1) {
				response.sendRedirect(servletRequest.getContextPath() + "/index");
			}
			if(path.endsWith("/index")){
				//(2访问已有session的index页面,放行
				return true;
			}else{
				//(3访问已有session的非index页面,重定向到index
				//response.sendRedirect(servletRequest.getContextPath() + "/index");
			}
		}
		
		
		return true;
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
