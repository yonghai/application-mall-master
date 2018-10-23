package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 子订单类
 * @author Administrator
 *id			oid			productName  productPic 	amount	property	number
子订单号	父订单号	商品名称		商品图片	单价	属性		数量
 */
public class OrderItem extends PageDto{
	private Integer id;//子订单号
	private String oid;//父订单号
	private String goodsName;//商品名称
	private String pic;//商品图片
	private Double amount;//单价
	private String property;//属性
	private Integer number;//购买数量
	private Integer goodsId;//商品id
	
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
