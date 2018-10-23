package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.WxUserLoginRecordCustom;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class WxUserLoginRecordVo {
	private String longitude;
	private String latitude;
	
	
	
	
	private WxUserLoginRecordCustom wxUserLoginRecordCustom;

	public WxUserLoginRecordCustom getWxUserLoginRecordCustom() {
		return wxUserLoginRecordCustom;
	}

	public void setWxUserLoginRecordCustom(
			WxUserLoginRecordCustom wxUserLoginRecordCustom) {
		this.wxUserLoginRecordCustom = wxUserLoginRecordCustom;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
}
