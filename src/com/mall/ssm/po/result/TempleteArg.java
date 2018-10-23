package com.mall.ssm.po.result;

/**
 * 模板消息
 * @author Administrator
 *
 */
public class TempleteArg {
    private String form_id;//form id
	private String page;
	private String orderNumber;
	private String dateAdd;
	private String amount;
	private Integer type = 1;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public TempleteArg(String form_id, String page, String orderNumber,
			String dateAdd, String amount) {
		super();
		this.form_id = form_id;
		this.page = page;
		this.orderNumber = orderNumber;
		this.dateAdd = dateAdd;
		this.amount = amount;
	}
	public TempleteArg() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
