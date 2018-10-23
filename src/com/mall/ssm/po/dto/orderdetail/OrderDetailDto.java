package com.mall.ssm.po.dto.orderdetail;

import java.io.Serializable;
import java.util.List;

import com.mall.ssm.po.custom.OrderCustom;
import com.mall.ssm.po.custom.OrderItemCustom;

public class OrderDetailDto implements Serializable{
	private OrderCustom orderInfo;
	private Logistics logistics;
	private LogisticsTraces logisticsTraces;
	private List<OrderItemCustom> goods;
	public OrderCustom getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(OrderCustom orderInfo) {
		this.orderInfo = orderInfo;
	}
	public Logistics getLogistics() {
		return logistics;
	}
	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}
	public LogisticsTraces getLogisticsTraces() {
		return logisticsTraces;
	}
	public void setLogisticsTraces(LogisticsTraces logisticsTraces) {
		this.logisticsTraces = logisticsTraces;
	}
	public List<OrderItemCustom> getGoods() {
		return goods;
	}
	public void setGoods(List<OrderItemCustom> goods) {
		this.goods = goods;
	}
	public OrderDetailDto(OrderCustom orderInfo, Logistics logistics,
			LogisticsTraces logisticsTraces, List<OrderItemCustom> goods) {
		super();
		this.orderInfo = orderInfo;
		this.logistics = logistics;
		this.logisticsTraces = logisticsTraces;
		this.goods = goods;
	}
	public OrderDetailDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
