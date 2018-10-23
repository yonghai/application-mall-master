package com.mall.ssm.po.pay;

import java.io.Serializable;

public class Payment implements Serializable{
	 private String timeStamp;
     private String nonceStr;
     private String prepayId;
     private String signType="MD5";
     private String paySign;
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Payment(String timeStamp, String nonceStr, String prepayId,
			String signType, String paySign) {
		super();
		this.timeStamp = timeStamp;
		this.nonceStr = nonceStr;
		this.prepayId = prepayId;
		this.signType = signType;
		this.paySign = paySign;
	}
     
}
