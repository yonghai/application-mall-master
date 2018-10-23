package com.mall.ssm.po;

/**
 * 积分类
 * @author Administrator
 *
 */
public class Integral {
	private String id;//用户标志
	private Integer total;//总积分
	private Integer available;//可用积分
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	public Integral(String id, Integer total, Integer available) {
		super();
		this.id = id;
		this.total = total;
		this.available = available;
	}
	public Integral() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
