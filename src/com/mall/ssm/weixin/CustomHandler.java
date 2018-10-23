package com.mall.ssm.weixin;

public class CustomHandler {
	private String appid;//appid
	private String secret;//secret
	private String templete_Order_Success;
	private String templete_Purchase_Success;
	
	
	private String wxLoginUrl = "https://api.weixin.qq.com/sns/jscode2session";
	private String access_tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
	private String access_tokenParam = "grant_type=client_credential";
	private String sendTempleteUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getWxLoginUrl() {
		return wxLoginUrl;
	}
	public void setWxLoginUrl(String wxLoginUrl) {
		this.wxLoginUrl = wxLoginUrl;
	}
	public String getAccess_tokenUrl() {
		return access_tokenUrl;
	}
	public void setAccess_tokenUrl(String access_tokenUrl) {
		this.access_tokenUrl = access_tokenUrl;
	}
	public String getAccess_tokenParam() {
		return access_tokenParam;
	}
	public void setAccess_tokenParam(String access_tokenParam) {
		this.access_tokenParam = access_tokenParam;
	}
	public String getSendTempleteUrl() {
		return sendTempleteUrl;
	}
	public void setSendTempleteUrl(String sendTempleteUrl) {
		this.sendTempleteUrl = sendTempleteUrl;
	}
	public String getTemplete_Order_Success() {
		return templete_Order_Success;
	}
	public void setTemplete_Order_Success(String templete_Order_Success) {
		this.templete_Order_Success = templete_Order_Success;
	}
	public String getTemplete_Purchase_Success() {
		return templete_Purchase_Success;
	}
	public void setTemplete_Purchase_Success(String templete_Purchase_Success) {
		this.templete_Purchase_Success = templete_Purchase_Success;
	}
}
