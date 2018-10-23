package com.mall.ssm.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mall.ssm.base.Code;
import com.mall.ssm.po.custom.WxUserCustom;
import com.mall.ssm.po.custom.WxUserLoginRecordCustom;
import com.mall.ssm.po.dto.xcx.MallName;
import com.mall.ssm.po.dto.xcx.R;
import com.mall.ssm.po.pay.PayUtil;
import com.mall.ssm.po.pay.Payment;
import com.mall.ssm.po.pay.RequestPay;
import com.mall.ssm.po.pay.WxPayConfig;
import com.mall.ssm.po.phone.PhoneNumber;
import com.mall.ssm.po.result.AccessToken;
import com.mall.ssm.po.result.SendTempleteMsg;
import com.mall.ssm.po.result.TempleteArg;
import com.mall.ssm.po.result.TempleteResult;
import com.mall.ssm.po.vo.WxUserLoginRecordVo;
import com.mall.ssm.po.vo.WxUserVo;
import com.mall.ssm.service.redis.Redis;
import com.mall.ssm.service.xcx.WeixinService;
import com.mall.ssm.util.AES;
import com.mall.ssm.util.Tool;

@Controller
public class XcxController {
	@Autowired
	private WeixinService weixinService;
	@Autowired
	private Redis<Object> redis;
	@Autowired
	private WxPayConfig wxPayConfig;
	
	public void setWxPayConfig(WxPayConfig wxPayConfig) {
		this.wxPayConfig = wxPayConfig;
	}

	/**
	 * 获取键值对
	 * @param request
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/xcx/config/get-value", method = RequestMethod.POST)
	public @ResponseBody R<MallName> getValue(HttpServletRequest request,String key)
		throws Exception {
		MallName mall = new MallName("学习商城");
		R<MallName> r = new R<MallName>();
		r.setData(mall);
		r.setCode(Code.OK);
		r.setMsg("success");
		return r;
	}
	
	/**
	 * 登录
	 * @param request
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/wxapp/login", method = RequestMethod.POST)
	public @ResponseBody R<WxUserVo> WxAppLogin(HttpServletRequest request,String code)
		throws Exception {//{"session_key":"ajHkeSIFHcUhznh\/GZM8VQ==","openid":"oEHj40FVg5Fvn4OklRO3BtejU5FU"}
		R<WxUserVo> r = new R<WxUserVo>();
	
		String result = weixinService.login(code);
		WxUserVo custom = JSONObject.parseObject(result, WxUserVo.class);
		
		if(custom.getWxUserCustom() == null){
			custom.setWxUserCustom(new WxUserCustom());
		}
		custom.getWxUserCustom().setAppid(custom.getOpenid());
		
		WxUserCustom user = weixinService.get(custom);
		
		if(user == null){//新用户
			r.setCode(Code.NO_FOUND_USER);
			/*
			 * 新用户自动注册
			 */
			custom.getWxUserCustom().setState(0);
			custom.getWxUserCustom().setAddtime(Tool.getyyyyMMddHHmm());
			weixinService.insert(custom);
			custom.setWxUserCustom(null);
		}else{//老用户
			if(user.getState() == 0){//1禁用  0正常
				r.setCode(Code.OK);
				custom.setWxUserCustom(user);
			}else{
				WxUserLoginRecordVo loginRecordVo = new WxUserLoginRecordVo();
				loginRecordVo.setWxUserLoginRecordCustom(new WxUserLoginRecordCustom());
				loginRecordVo.getWxUserLoginRecordCustom().setAppid(user.getAppid());
				loginRecordVo.getWxUserLoginRecordCustom().setLogintime(Tool.getyyyyMMddHHmmss());
				weixinService.insertLoginRecord(loginRecordVo);
				r.setCode(Code.User_Forbidden);
				r.setMsg("该用户被禁用,请联系客服处理!");
				return r;
			}
			
			
		}
		
		r.setMsg("success");
		
		/*
		 * 将该用户创建token
		 * session_key md5
		 */
		String newSessionKey = Tool.MD5(custom.getSession_key());
		custom.setSession_key(newSessionKey);
		
		/*
		 * 单用户登录
		 */
		if(redis.exist(custom.getOpenid())){
			String oldToken = redis.get(custom.getOpenid());
			if(!Tool.strIsEmpty(oldToken)){
				redis.delete(oldToken);
			}
		}
		redis.add(newSessionKey, custom.getOpenid(), 1*60*1);//token保存一小时
		redis.add(custom.getOpenid(), newSessionKey, 1*60*1);
		r.setData(custom);
		return r;
	}

	/**
	 * 检查token是否有效
	 * @param request
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/check-token", method = RequestMethod.POST)
	public @ResponseBody R<String> checkWxUserToken(HttpServletRequest request,String token)
		throws Exception {
		long l1 = System.currentTimeMillis();
		R<String> r = new R<String>();
		
		boolean ret = false;
		if(token == null || token.trim().length() == 0){
			r.setMsg("token is null");
		}else{
			if(redis.exist(token)){
				String value1 = redis.get(token);
				if(!Tool.strIsEmpty(value1)&&redis.exist(value1)){
					String value2 = redis.get(value1);
					if(!Tool.strIsEmpty(value2)&& token.equals(value2)){
						ret = true;
					}else{
						List<String> keys = new ArrayList<String>();
						keys.add(token);
						keys.add(value1);
						keys.add(value2);
						redis.delete(keys);
					}
				}
			}
		}
		if(ret){
			r.setData(redis.get(token));
			r.setMsg("query success");
		}else{//token不通过
			r.setMsg("query failed");
		}
		
		r.setCode(Code.OK);
		long l2 = System.currentTimeMillis();
		System.out.println("checkWxUserToken耗时:"+(l2-l1));
		return r;
	}
	
	/**
	 * 更新微信用户信息
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/update", method = RequestMethod.POST)
	public @ResponseBody R<String> updateWxUser(HttpServletRequest request,WxUserVo user)
		throws Exception {
		R<String> r = new R<String>();
		
		if(user.getToken() == null || user.getToken().trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(user.getToken())){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String appid = redis.get(user.getToken());
		
		if(appid == null || appid.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		try {
			WxUserVo entity = new WxUserVo();
			entity.setWxUserCustom(new WxUserCustom());
			entity.getWxUserCustom().setAppid(appid);
			
			user.getWxUserCustom().setAppid(appid);
			/*昵称转码*/
			String nickName = user.getWxUserCustom().getNickName();
			nickName = java.net.URLDecoder.decode(nickName,"UTF-8"); 
			user.getWxUserCustom().setNickName(nickName);
			weixinService.update(user);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		r.setMsg("success");
		r.setCode(Code.OK);
		return r;
	}
	
	/**
	 * 发送模板消息
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/template-msg/put", method = RequestMethod.POST)
	public @ResponseBody R<String> template_msg_put(HttpServletRequest request,TempleteArg arg,String token)
		throws Exception {
		R<String> r = new R<String>();
		if(arg.getAmount()!=null&&arg.getAmount().trim().length()>0){
			arg.setAmount(java.net.URLDecoder.decode(arg.getAmount(),"UTF-8"));
		}
		if(arg.getDateAdd()!=null&&arg.getDateAdd().trim().length()>0){
			arg.setDateAdd(java.net.URLDecoder.decode(arg.getDateAdd(),"UTF-8"));
		}else{
			arg.setDateAdd(Tool.getyyyyMMddHHmmss());
		}
		if(arg.getForm_id()!=null&&arg.getForm_id().trim().length()>0){
			arg.setForm_id(java.net.URLDecoder.decode(arg.getForm_id(),"UTF-8"));
		}
		if(arg.getOrderNumber()!=null&&arg.getOrderNumber().trim().length()>0){
			arg.setOrderNumber(java.net.URLDecoder.decode(arg.getOrderNumber(),"UTF-8"));
		}
		if(arg.getPage()!=null&&arg.getPage().trim().length()>0){
			arg.setPage(java.net.URLDecoder.decode(arg.getPage(),"UTF-8"));
		}
		SendTempleteMsg msg = new SendTempleteMsg(arg);
		
		AccessToken accessToken = weixinService.getAccess_token();
		String aToken = accessToken.getAccess_token();
		String templete_id = weixinService.getCustomHandler().getTemplete_Order_Success();
		if(arg.getType() == null || arg.getType() == 2){
			templete_id = weixinService.getCustomHandler().getTemplete_Purchase_Success();
		}
		
		if(token == null || token.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(token)){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		String openid = redis.get(token);
		
		msg.setTouser(openid);
		msg.setTemplate_id(templete_id);
		
		TempleteResult result = weixinService.sendTempleteMsg(msg,aToken);
		if(result.getErrcode() == 0){
			r.setMsg("success");
			r.setCode(Code.OK);
		}else{
			r.setMsg(result.getErrmsg());
			r.setCode(Code.ERROR);
		}
		
		
		return r;
	}
	
	/**
	 * 绑定手机号
	 * @param request
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/wxapp/bindMobile")
	public @ResponseBody R<PhoneNumber> bindMobile(HttpServletRequest request,String token,String encryptedData,String iv,String code)
		throws Exception {
		R<PhoneNumber> r = new R<PhoneNumber>();
	
		if(token == null || token.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(token)){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String appid = redis.get(token);
		
		String result = weixinService.login(code);
		String seessionKey = JSONObject.parseObject(result, WxUserVo.class).getSession_key();
		
		String json = AES.wxDecrypt(encryptedData, seessionKey, iv);
		PhoneNumber phoneNumber = JSONObject.parseObject(json, PhoneNumber.class);
		if(phoneNumber == null){
			json = AES.wxDecrypt(encryptedData, seessionKey, iv);
			phoneNumber = JSONObject.parseObject(json, PhoneNumber.class);
			if(phoneNumber == null){
				json = AES.wxDecrypt(encryptedData, seessionKey, iv);
				phoneNumber = JSONObject.parseObject(json, PhoneNumber.class);
			}
		}
		if(phoneNumber == null){
			r.setCode(Code.ERROR);
			r.setMsg("获取手机号码失败,请重新尝试.");
		}else{
			WxUserVo uservo = new WxUserVo();
			uservo.setWxUserCustom(new WxUserCustom());
			uservo.getWxUserCustom().setAppid(appid);
			WxUserCustom user = weixinService.get(uservo);
			user.setUserMobile(phoneNumber.getPhoneNumber());
			uservo.setWxUserCustom(user);
			weixinService.update(uservo);
			
			r.setCode(Code.OK);
			r.setData(phoneNumber);
		}
		
		return r;
	}
	/**
	 * 获取保存的微信用户信息
	 * @param request
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/user/detail", method = RequestMethod.POST)
	public @ResponseBody R<WxUserCustom> userdetail(HttpServletRequest request,String token)
		throws Exception {
		R<WxUserCustom> r = new R<WxUserCustom>();
	
		if(token == null || token.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(token)){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String appid = redis.get(token);
		
		if(appid == null || appid.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		WxUserVo uservo = new WxUserVo();
		uservo.setWxUserCustom(new WxUserCustom());
		uservo.getWxUserCustom().setAppid(appid);
		WxUserCustom user = weixinService.get(uservo);
		
		if(user == null){//新用户
			r.setCode(Code.ERROR);
			r.setMsg("no data found");
			return r;
		}
		r.setCode(Code.OK);
		r.setMsg("success");
		r.setData(user);
		return r;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="/pay/wxapp/get-pay-data")
	public @ResponseBody R<Payment> getPayData(HttpServletRequest request,RequestPay pay,String token)
		throws Exception {
		R<Payment> r = new R<Payment>();
		Payment payment = new Payment();

		if(pay.getPayName()!=null){
			pay.setPayName(java.net.URLDecoder.decode(pay.getPayName(),"UTF-8"));
		}
		if(pay.getRemark()!=null){
			pay.setRemark(java.net.URLDecoder.decode(pay.getRemark(),"UTF-8"));
		}
		
		if(token == null || token.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		if(!redis.exist(token)){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		String openid = redis.get(token);
		
		if(openid == null || openid.trim().length() == 0){
			r.setCode(Code.ERROR);
			r.setMsg("token is invalid");
			return r;
		}
		
		
		/*
		 * 调用微信支付
		 */
		try {
			payment = weixinService.wxPay(request, pay, openid);
			r.setCode(Code.OK);
			r.setMsg("success");
			r.setData(payment);
		} catch (RuntimeException e) {
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
			e.printStackTrace();
		}
		
		
		return r;
	}
	
	
	
	
	@RequestMapping(value="/pay/wxapp/notyfy")
	public @ResponseBody R<Payment> getPayDataNotyfy(HttpServletRequest request,HttpServletResponse response)
		throws Exception {
		R<Payment> r = new R<Payment>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);

        Map map = PayUtil.doXMLParse(notityXml);

        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
            Map<String, String> validParams = PayUtil.paraFilter(map);  //回调验签时需要去除sign和空值参数

            //根据微信官网的介绍，此处不仅对回调的参数进行验签，还需要对返回的金额与系统订单的金额进行比对等
            if(PayUtil.verify(validParams.toString(), map.get("sign").toString(), wxPayConfig.getKey(), "utf-8")){
                /**此处添加自己的业务逻辑代码start**/
            	

                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");


        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
		
		
		
		r.setCode(Code.OK);
		r.setMsg("success");
		return r;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 后台用户列表
	 */
	@RequestMapping(value="/user/wxUserList", method = RequestMethod.POST)
	public @ResponseBody R<Map<Object, Object>> wxUserList(WxUserVo wxUserVo)
		throws Exception {
		R<Map<Object, Object>> r = new R<Map<Object, Object>>();
		Map<Object, Object> map = new HashMap<Object, Object>();
		try {
			if(wxUserVo.getWxUserCustom().getPage()<1){
				wxUserVo.getWxUserCustom().setPage(1);
			}
			if(wxUserVo.getWxUserCustom().getLimit()<1){
				wxUserVo.getWxUserCustom().setLimit(20);
			}
			
			List<WxUserCustom> wxUsers = weixinService.getListByPage(wxUserVo);
			long count = weixinService.getCount(wxUserVo);
			r.setCode(Code.OK);
			r.setMsg("success");
			map.put("data",wxUsers);  
			map.put("recordsTotal", count);  
			map.put("recordsFiltered", count);  
			map.put("draw", System.currentTimeMillis());
			r.setData(map);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	/**
	 * 改变用户状态
	 */
	@RequestMapping(value="/user/changeState", method = RequestMethod.POST)
	public @ResponseBody R<String> changeState(HttpServletRequest request)
		throws Exception {
		R<String> r = new R<String>();
		try {
			String stateStr = request.getParameter("state");
			if(stateStr == null || stateStr.trim().length() == 0){
				r.setCode(Code.ERROR);
				r.setMsg("invalid param state!");
				return r;
			}
			String idStr = request.getParameter("id");
			if(idStr == null || idStr.trim().length() == 0){
				r.setCode(Code.ERROR);
				r.setMsg("invalid param id!");
				return r;
			}
			int state = Integer.parseInt(stateStr);
			WxUserVo wxUserVo = new WxUserVo();
			wxUserVo.setWxUserCustom(new WxUserCustom());
			wxUserVo.getWxUserCustom().setState(state);
			wxUserVo.getWxUserCustom().setAppid(idStr);
			
			weixinService.update(wxUserVo);
			r.setCode(Code.OK);
			r.setMsg("success");
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value="/user/delete", method = RequestMethod.POST)
	public @ResponseBody R<String> delete(HttpServletRequest request)
		throws Exception {
		R<String> r = new R<String>();
		try {
			String idStr = request.getParameter("id");
			if(idStr == null || idStr.trim().length() == 0){
				r.setCode(Code.ERROR);
				r.setMsg("invalid param id!");
				return r;
			}
			weixinService.delete(Integer.parseInt(idStr));
			r.setCode(Code.OK);
			r.setMsg("success");
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
	
	
	
	/**
	 * 
	 */
	@RequestMapping(value="/user/checkUserValid", method = RequestMethod.POST)
	public @ResponseBody R<String> checkUserValid(String token,WxUserLoginRecordVo loginRecordVo)
		throws Exception {
		R<String> r = new R<String>();
		try {
			if(token == null || token.trim().length() == 0){
				r.setCode(Code.ERROR);
				r.setMsg("token is invalid");
				return r;
			}
			if(!redis.exist(token)){
				r.setCode(Code.ERROR);
				r.setMsg("token is invalid");
				return r;
			}
			
			String openid = redis.get(token);
			
			String device = java.net.URLDecoder.decode(loginRecordVo.getWxUserLoginRecordCustom().getDevice(),"UTF-8"); 
			loginRecordVo.getWxUserLoginRecordCustom().setDevice(device);
			loginRecordVo.getWxUserLoginRecordCustom().setAppid(openid);
			loginRecordVo.getWxUserLoginRecordCustom().setLogintime(Tool.getyyyyMMddHHmmss());
			
			if(loginRecordVo.getWxUserLoginRecordCustom().getLocation()!=null){
				String location = java.net.URLDecoder.decode(loginRecordVo.getWxUserLoginRecordCustom().getLocation(),"UTF-8"); 
				loginRecordVo.getWxUserLoginRecordCustom().setLocation(location);
			}else{
				loginRecordVo.getWxUserLoginRecordCustom().setLocation("");
			}
			
			weixinService.insertLoginRecord(loginRecordVo);
			r.setCode(Code.OK);
			r.setMsg("success");
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setCode(Code.ERROR);
			r.setMsg(e.getMessage());
		}
		return r;
	}
}
