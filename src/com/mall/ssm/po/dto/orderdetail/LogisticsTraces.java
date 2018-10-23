package com.mall.ssm.po.dto.orderdetail;

import java.io.Serializable;

public class LogisticsTraces implements Serializable{
	private String AcceptStation;
	private String AcceptTime;
	public String getAcceptStation() {
		return AcceptStation;
	}
	public void setAcceptStation(String acceptStation) {
		AcceptStation = acceptStation;
	}
	public String getAcceptTime() {
		return AcceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		AcceptTime = acceptTime;
	}
	public LogisticsTraces(String acceptStation, String acceptTime) {
		super();
		AcceptStation = acceptStation;
		AcceptTime = acceptTime;
	}
	public LogisticsTraces() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
