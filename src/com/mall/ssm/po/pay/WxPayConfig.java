package com.mall.ssm.po.pay;

/**
 * 小程序微信支付的配置文件
 * @author 
 *
 */
public class WxPayConfig {
	//微信支付的商户id
	private String mch_id;
	//微信支付的商户密钥
	private String key;
	//支付成功后的服务器回调url
	private String notify_url;
	//签名方式，固定值
	private String SIGNTYPE;
	//交易类型，小程序支付的固定值为JSAPI
	private String TRADETYPE;
	//微信统一下单接口地址
	private String pay_url;
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getSIGNTYPE() {
		return SIGNTYPE;
	}
	public void setSIGNTYPE(String signtype) {
		SIGNTYPE = signtype;
	}
	public String getTRADETYPE() {
		return TRADETYPE;
	}
	public void setTRADETYPE(String tradetype) {
		TRADETYPE = tradetype;
	}
	public String getPay_url() {
		return pay_url;
	}
	public void setPay_url(String pay_url) {
		this.pay_url = pay_url;
	}
}
