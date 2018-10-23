package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 订单类
 * @author Administrator
 *id		  	 dateAdd		amountReal		amountLogistics	 amount		status    orderNumber  remark 	appid  	productIds productPics	trackingNumber	linkMan	
订单id     订单创建时间	   实际所付金额			运费		商品金额	订单状态   订单号		备注	用户id    商品id	商品图片		物流号		收货人
logistics	mobile		address		discount
是否物流	联系方式	收货地址	折扣id
 */
public class Order extends PageDto{
	private Integer id;//订单id
	private String dateAdd;//v订单创建时间
	private Double amountReal;// 实际所付金额	
	private Double amountLogistics;//运费	
	private Double amount;//商品金额
	private Integer status;//订单状态
	private String orderNumber;//订单号	
	private String remark;//备注	
	private String appid;//用户id
	private String productIds;//商品id
	private String productPics;//商品图片
	private String trackingNumber;//物流号
	private String linkMan;//收货人
	private Integer logistics;//是否物流
	private String mobile;//联系方式
	private String address;//收货地址	
	private Integer discount;//折扣id
	private Integer couponId;//优惠券id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDateAdd() {
		return dateAdd;
	}
	public void setDateAdd(String dateAdd) {
		this.dateAdd = dateAdd;
	}
	public Double getAmountReal() {
		return amountReal;
	}
	public void setAmountReal(Double amountReal) {
		this.amountReal = amountReal;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public Double getAmountLogistics() {
		return amountLogistics;
	}
	public void setAmountLogistics(Double amountLogistics) {
		this.amountLogistics = amountLogistics;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getProductIds() {
		return productIds;
	}
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
	public String getProductPics() {
		return productPics;
	}
	public void setProductPics(String productPics) {
		this.productPics = productPics;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public Integer getLogistics() {
		return logistics;
	}
	public void setLogistics(Integer logistics) {
		this.logistics = logistics;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Order(Integer id, String dateAdd, Double amountReal,
			Double amountLogistics, Double amount, Integer status,
			String orderNumber, String remark, String appid, String productIds,
			String productPics, String trackingNumber, String linkMan,
			Integer logistics, String mobile, String address, Integer discount) {
		super();
		this.id = id;
		this.dateAdd = dateAdd;
		this.amountReal = amountReal;
		this.amountLogistics = amountLogistics;
		this.amount = amount;
		this.status = status;
		this.orderNumber = orderNumber;
		this.remark = remark;
		this.appid = appid;
		this.productIds = productIds;
		this.productPics = productPics;
		this.trackingNumber = trackingNumber;
		this.linkMan = linkMan;
		this.logistics = logistics;
		this.mobile = mobile;
		this.address = address;
		this.discount = discount;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
