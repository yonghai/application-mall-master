package com.mall.ssm.po.dto.orderdetail;

import java.io.Serializable;

public class Logistics implements Serializable{
	private String trackingNumber;//订单物流号
	private String linkMan;//联系人
	private String mobile;//手机号
	private String address;//地址
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Logistics(String trackingNumber, String linkMan, String mobile,
			String address) {
		super();
		this.trackingNumber = trackingNumber;
		this.linkMan = linkMan;
		this.mobile = mobile;
		this.address = address;
	}
	public Logistics() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
