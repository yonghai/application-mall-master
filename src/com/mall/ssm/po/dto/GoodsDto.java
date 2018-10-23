package com.mall.ssm.po.dto;

import java.io.Serializable;

/**
 * 接收到的订单商品json转换对象
 * @author Administrator
 *
 */
public class GoodsDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer goodsId;
	private Integer number;
	private String propertyChildIds;
	private Integer logisticsType;
	private Integer inviter_id;
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getPropertyChildIds() {
		return propertyChildIds;
	}
	public void setPropertyChildIds(String propertyChildIds) {
		this.propertyChildIds = propertyChildIds;
	}
	public Integer getLogisticsType() {
		return logisticsType;
	}
	public void setLogisticsType(Integer logisticsType) {
		this.logisticsType = logisticsType;
	}
	public Integer getInviter_id() {
		return inviter_id;
	}
	public void setInviter_id(Integer inviter_id) {
		this.inviter_id = inviter_id;
	}
	
}
