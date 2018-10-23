package com.mall.ssm.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.ssm.po.custom.UserCustom;
import com.mall.ssm.po.vo.UserVo;
import com.mall.ssm.service.user.UserService;
import com.mall.ssm.util.Tool;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 登录认证
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public String authenticate(HttpServletRequest req,HttpServletResponse response,String token,UserVo user)throws Exception{
		//1判断用户是否是重复提交
		boolean b = isRepeatSubmit(req,token);
        if(b==true){
        	//重复提交处理   此处需要改进
        	req.setAttribute("msg", "请不要重复提交");
        	return "login";
        }
        //2检验参数合法性
        
        
        //3.查询用户
        String pwd = Tool.MD5(user.getUserCustom().getPwd());
        user.getUserCustom().setPwd(null);
        UserCustom userCustom  = userService.get(user);
        
        //4.如果无该用户,或密码不正确
        if(userCustom == null){
        	//如果无该用户
        	req.setAttribute("msg", "无该用户");
        	return "login";
        }
        if(!userCustom.getPwd().equals(pwd)){
        	//密码不正确
        	userCustom.setPwd("");
        	req.setAttribute("msg", "密码不正确");
        	req.setAttribute("user", userCustom);
        	return "login";
        }
        
        /*
         * 5.用户状态
         * 0:未激活 1：正常 2：被禁用
         */
        if(userCustom.getStatus() == 1){
        	//正常
        }else if(userCustom.getStatus() == 0){
        	//未激活
        	userCustom.setPwd("");
        	req.setAttribute("msg", "账户未激活,请联系管理员");
        	req.setAttribute("user",userCustom);
        	return "login";
        }else{
        	//被禁用
        	userCustom.setPwd("");
        	req.setAttribute("msg", "账户被禁用,请联系管理员");
        	req.setAttribute("user",userCustom);
        	return "login";
        }
        
        /*
         * 6.用户放入session 
         * 移除session中的token
         */
        userCustom.setPwd("");
        userCustom.setEmail("");
        userCustom.setMobile("");
        req.getSession().setAttribute("user", userCustom);
        req.getSession().removeAttribute("token");
        
        /*
         * 7.记住我
         */
        String remember = req.getParameter("remember");
        if(remember == null || "".equals(remember)){
        	//不用记住我
        }else{
        	//记住我
        	// 保存cookie，为了login.jsp页面可以记住当前用户名
			String name = "userCustom.email";
			String value = URLEncoder.encode(user.getUserCustom().getEmail(), "UTF-8");
			Cookie cookie = new Cookie(name, value);
			cookie.setMaxAge(1 * 60 * 15);//15分钟
			response.addCookie(cookie);
        	
        	
        }
        /*
         * 8转发页面
         */
        //response.sendRedirect(req.getContextPath() + "/index");
		return "index";
	}
	/**
	 * 退出
	 * @param req
	 * @param response
	 * @param token
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest req,HttpServletResponse response)throws Exception{
		req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("token");
		return "login";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
     * 判断客户端提交上来的令牌和服务器端生成的令牌是否一致
     * @param request
     * @return 
     *         true 用户重复提交了表单 
     *         false 用户没有重复提交表单
     */
    private boolean isRepeatSubmit(HttpServletRequest req,String token) {
        String client_token = token;
        //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
        if(client_token==null){
            return true;
        }
        //取出存储在Session中的token
        String server_token = (String) req.getSession().getAttribute("token");
        //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
        if(server_token==null){
            return true;
        }
        //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if(!client_token.equals(server_token)){
            return true;
        }
        
        return false;
    }
    protected void getRequestParams(HttpServletRequest req)
		throws IOException {
		for(Object key : req.getParameterMap().keySet()){
			System.out.println("=========================="+key+":"+req.getParameter(key.toString()));
		}
	}
}
