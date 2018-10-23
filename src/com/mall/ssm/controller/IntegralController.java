package com.mall.ssm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mall.ssm.base.Code;
import com.mall.ssm.base.IntegralOperateType;
import com.mall.ssm.base.IntegralType;
import com.mall.ssm.po.custom.IntegralCustom;
import com.mall.ssm.po.custom.IntegralDetailCustom;
import com.mall.ssm.po.dto.xcx.R;
import com.mall.ssm.po.vo.IntegralDetailVo;
import com.mall.ssm.po.vo.IntegralVo;
import com.mall.ssm.service.integral.IntegralService;
import com.mall.ssm.service.redis.Redis;
import com.mall.ssm.util.Tool;

@Controller
public class IntegralController {
	@Autowired
	private Redis<Object> redis;
	
	@Autowired
	private IntegralService integralService;
	
	/**
	 * 小程序调用
	 * @param swiper
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * 我的积分
	 */
	@RequestMapping(value="/user/amount", method = RequestMethod.POST)
	public @ResponseBody R<IntegralCustom> myDiscounts(HttpServletRequest request,String token)
		throws Exception {
		R<IntegralCustom> r = new R<IntegralCustom>();
		
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
		
		IntegralVo iv = new IntegralVo();
		IntegralCustom ic = new IntegralCustom();
		ic.setId(appid);
		iv.setIntegralCustom(ic);
		
		try {
			ic = integralService.getIntegral(iv);
			if(ic == null){
				ic = new IntegralCustom();
				ic.setAvailable(0);
				ic.setTotal(0);
				ic.setId(appid);
				iv.setIntegralCustom(ic);
				integralService.insertIntegral(iv);
			}
			r.setMsg("success");
			r.setCode(Code.OK);
			r.setData(ic);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	
	/**
	 * 今天是否签到
	 * @param request
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/score/today-signed", method = RequestMethod.POST)
	public @ResponseBody R<Integer> today_signed(HttpServletRequest request,String token)
		throws Exception {
		R<Integer> r = new R<Integer>();
		
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
		
		IntegralDetailVo iv = new IntegralDetailVo();
		IntegralDetailCustom ic = new IntegralDetailCustom();
		ic.setAppid(appid);
		iv.setIntegralDetailCustom(ic);
		
		try {
			ic = integralService.getLaseIntegralDetail(appid);
			Integer result = 0;
			if(ic == null){//未签到
				result = 0;
			}else{
				String time = ic.getTime();
				 SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
				 Date date=sDateFormat.parse(time);
				 
				 if(Tool.sameDate(date,new Date())){//已签到
					 result = 1;
				 }else{
					 result = 0;
				 }
			}
			r.setMsg("success");
			r.setCode(Code.OK);
			r.setData(result);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	
	
	
	/**
	 * 签到
	 * @param request
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/score/sign", method = RequestMethod.POST)
	public @ResponseBody R<Integer> score_sign(HttpServletRequest request,String token)
		throws Exception {
		R<Integer> r = new R<Integer>();
		
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
		
		IntegralDetailVo iv = new IntegralDetailVo();
		IntegralDetailCustom ic = new IntegralDetailCustom();
		ic.setAppid(appid);
		iv.setIntegralDetailCustom(ic);
		
		try {
			IntegralVo ivv = new IntegralVo();
			IntegralCustom icc = new IntegralCustom();
			icc.setId(appid);
			ivv.setIntegralCustom(icc);
			icc = integralService.getIntegral(ivv);
			if(icc == null){
				icc = new IntegralCustom();
				icc.setAvailable(0);
				icc.setTotal(0);
				icc.setId(appid);
				ivv.setIntegralCustom(icc);
				integralService.insertIntegral(ivv);
			}
			
			ic.setAppid(appid);
			ic.setOperation(getScore());
			ic.setAvailable(icc.getAvailable());
			ic.setOperationtype(IntegralOperateType.ADD);
			ic.setType(IntegralType.Sign);
			ic.setTime(Tool.getyyyyMMddHHmmss());
			iv.setIntegralDetailCustom(ic);
			integralService.insertIntegralDetail(iv);
			icc.setTotal(icc.getTotal()+ic.getOperation());
			icc.setAvailable(icc.getAvailable()+ic.getOperation());
			ivv.setIntegralCustom(icc);
			integralService.updateIntegral(ivv);
			Integer result = 0;
			
			r.setMsg("success");
			r.setCode(Code.OK);
			r.setData(result);
		} catch (RuntimeException e) {
			e.printStackTrace();
			r.setMsg(e.getMessage());
			r.setCode(Code.ERROR);
		}
		
		return r;
	}
	
	
	/**
	 * 签到获得的积分
	 * @return
	 */
	private int getScore(){
		return 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
