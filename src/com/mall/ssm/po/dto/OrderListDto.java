package com.mall.ssm.po.dto;

import java.io.Serializable;
import java.util.List;

import com.mall.ssm.po.custom.OrderCustom;

public class OrderListDto implements Serializable{
	private List<OrderCustom> orderList;
	private List<List<OrderListPic>> goodsMap;
	public List<OrderCustom> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderCustom> orderList) {
		this.orderList = orderList;
	}
	
	public List<List<OrderListPic>> getGoodsMap() {
		return goodsMap;
	}
	public void setGoodsMap(List<List<OrderListPic>> goodsMap) {
		this.goodsMap = goodsMap;
	}
	
	public OrderListDto(List<OrderCustom> orderList,
			List<List<OrderListPic>> goodsMap) {
		super();
		this.orderList = orderList;
		this.goodsMap = goodsMap;
	}
	public OrderListDto() {
		super();
	}
}
