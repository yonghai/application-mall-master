package com.mall.ssm.po.phone;

import java.io.Serializable;

public class PhoneNumber implements Serializable{
	//{"phoneNumber":"18031873395","purePhoneNumber":"18031873395","countryCode":"86","watermark":{"timestamp":1530006435,"appid":"wx9c9d918323636f75"}}
	private String phoneNumber;
	private String purePhoneNumber;
	private Integer countryCode;
	private Watermark watermark;
	
	
	
	public PhoneNumber() {
		super();
	}



	public static class Watermark{
		private String timestamp;
		private String appid;
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public String getAppid() {
			return appid;
		}
		public void setAppid(String appid) {
			this.appid = appid;
		}
		public Watermark() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Watermark(String timestamp, String appid) {
			super();
			this.timestamp = timestamp;
			this.appid = appid;
		}
		
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public String getPurePhoneNumber() {
		return purePhoneNumber;
	}



	public void setPurePhoneNumber(String purePhoneNumber) {
		this.purePhoneNumber = purePhoneNumber;
	}



	public Integer getCountryCode() {
		return countryCode;
	}



	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}



	public Watermark getWatermark() {
		return watermark;
	}



	public void setWatermark(Watermark watermark) {
		this.watermark = watermark;
	}



	public PhoneNumber(String phoneNumber, String purePhoneNumber,
			Integer countryCode, Watermark watermark) {
		super();
		this.phoneNumber = phoneNumber;
		this.purePhoneNumber = purePhoneNumber;
		this.countryCode = countryCode;
		this.watermark = watermark;
	}
}
