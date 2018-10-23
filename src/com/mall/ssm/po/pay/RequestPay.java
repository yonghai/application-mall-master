package com.mall.ssm.po.pay;

import java.io.Serializable;

public class RequestPay implements Serializable{
	private Double money;
	private String remark;
	private String payName;
	private NextAction nextAction;
	public Double getMoney() {
		return money;
	}
	public Double getMoney100() {
		return money*100;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public NextAction getNextAction() {
		return nextAction;
	}
	public void setNextAction(NextAction nextAction) {
		this.nextAction = nextAction;
	}
	
}
