package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.OrderItemCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class OrderItemVo extends PageDto {
	
	private OrderItemCustom orderItemCustom;


	public OrderItemCustom getOrderItemCustom() {
		return orderItemCustom;
	}


	public void setOrderItemCustom(OrderItemCustom orderItemCustom) {
		this.orderItemCustom = orderItemCustom;
	}


	public OrderItemVo() {
		super();
	}
	
}
