package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.WxUserCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class WxUserVo extends PageDto {
	private WxUserCustom wxUserCustom;
	
	private String session_key;
	private String openid;
	private String token;
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public WxUserCustom getWxUserCustom() {
		return wxUserCustom;
	}

	public void setWxUserCustom(WxUserCustom wxUserCustom) {
		this.wxUserCustom = wxUserCustom;
	}
	
}
