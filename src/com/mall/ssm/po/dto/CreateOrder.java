package com.mall.ssm.po.dto;

import java.io.Serializable;

/**
 * 创建订单后返回结果
 * @author Administrator
 *
 */
public class CreateOrder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer isNeedLogistics;//是否需要物流,默认需要
	private Double amountTotle;//总费用
	private Double amountLogistics;//运费
	private String orderNumber;
	private String dateAdd;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getDateAdd() {
		return dateAdd;
	}
	public void setDateAdd(String dateAdd) {
		this.dateAdd = dateAdd;
	}
	public Integer getIsNeedLogistics() {
		return isNeedLogistics;
	}
	public void setIsNeedLogistics(Integer isNeedLogistics) {
		this.isNeedLogistics = isNeedLogistics;
	}
	public Double getAmountTotle() {
		return amountTotle;
	}
	public void setAmountTotle(Double amountTotle) {
		this.amountTotle = amountTotle;
	}
	public Double getAmountLogistics() {
		return amountLogistics;
	}
	public void setAmountLogistics(Double amountLogistics) {
		this.amountLogistics = amountLogistics;
	}
	public CreateOrder(Integer isNeedLogistics, Double amountTotle,
			Double amountLogistics) {
		super();
		this.isNeedLogistics = isNeedLogistics;
		this.amountTotle = amountTotle;
		this.amountLogistics = amountLogistics;
	}
	public CreateOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
