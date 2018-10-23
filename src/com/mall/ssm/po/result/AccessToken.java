package com.mall.ssm.po.result;
/**
 * 返回微信access_token
 * @author Administrator
 *
 */
public class AccessToken {
	private String access_token;//获取到的凭证
	private Integer expires_in;//凭证有效时间，单位：秒
	private Integer errcode;//
	private String errmsg;//
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}
