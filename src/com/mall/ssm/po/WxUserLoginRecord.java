package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 用户登录记录
 * @author Administrator
 *
 */
public class WxUserLoginRecord extends PageDto{
	private String appid;
	private String logintime;
	private String location;
	private String device;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public WxUserLoginRecord(String appid, String logintime, String location,
			String device) {
		super();
		this.appid = appid;
		this.logintime = logintime;
		this.location = location;
		this.device = device;
	}
	public WxUserLoginRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
