package com.mall.ssm.po.custom;

import java.util.List;

import com.mall.ssm.base.OrderStatus;

/**
 * Bean信息的扩展类
 * @author Administrator
 *
 */
public class OrderCustom extends com.mall.ssm.po.Order{
	//添加信息的扩展属性
	private WxUserCustom user;
	
	
	private List<OrderItemCustom> orderdetails;
	private String statusStr = "待付款";

	public String getStatusStr() {
		if(getStatus() == OrderStatus.daifukuan){
			statusStr = "待付款";
		}else if(getStatus() == OrderStatus.daifahuo){
			statusStr = "待发货";
		}else if(getStatus() == OrderStatus.daishouhuo){
			statusStr = "待收货";
		}else if(getStatus() == OrderStatus.daipingjia){
			statusStr = "待评价";
		}else if(getStatus() == OrderStatus.yiwancheng){
			statusStr = "已完成";
		}else{
			statusStr = "异常订单";
		}
		
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public List<OrderItemCustom> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<OrderItemCustom> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public WxUserCustom getUser() {
		return user;
	}

	public void setUser(WxUserCustom user) {
		this.user = user;
	}
	
}
