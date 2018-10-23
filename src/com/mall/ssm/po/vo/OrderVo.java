package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.OrderCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class OrderVo extends PageDto {
	
	private OrderCustom orderCustom;
	private String address;
	private Boolean calculate;
	private String code;
	private String goodsJsonStr;
	private String mobile;
	private String remark;
	private String token;
	private String linkMan;
	private Integer couponId;
	
	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public OrderCustom getOrderCustom() {
		return orderCustom;
	}

	public void setOrderCustom(OrderCustom orderCustom) {
		this.orderCustom = orderCustom;
	}

	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getCalculate() {
		return calculate;
	}

	public void setCalculate(Boolean calculate) {
		this.calculate = calculate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGoodsJsonStr() {
		return goodsJsonStr;
	}

	public void setGoodsJsonStr(String goodsJsonStr) {
		this.goodsJsonStr = goodsJsonStr;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public OrderVo() {
		super();
	}
	
}
