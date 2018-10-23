package com.mall.ssm.po;

/**
 * 积分详情类
 * @author Administrator
 *
 */
public class IntegralDetail {
	private int id;//记录id
	private String appid;//用户标志
	private Integer operation;//操作积分
	private Integer available;//可用积分
	private String operationtype;//操作类型
	private String type = "有效";//积分类型
	private String time;
	public IntegralDetail(int id, String appid, Integer operation,
			Integer available, String operationtype, String time) {
		super();
		this.id = id;
		this.appid = appid;
		this.operation = operation;
		this.available = available;
		this.operationtype = operationtype;
		this.time = time;
	}
	public IntegralDetail() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public Integer getOperation() {
		return operation;
	}
	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public String getOperationtype() {
		return operationtype;
	}
	public void setOperationtype(String operationtype) {
		this.operationtype = operationtype;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
