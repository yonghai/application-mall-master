package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 属性类
 * @author Administrator
 *
 */
public class Property extends PageDto{
	private Integer id;//属性id
	private String name;//属性名
	private Integer orderBy;//排序
	private String uid;//备用
	private String updateTime;//更新时间
	public Property(Integer id, String name, Integer orderBy, String uid,
			String updateTime) {
		super();
		this.id = id;
		this.name = name;
		this.orderBy = orderBy;
		this.uid = uid;
		this.updateTime = updateTime;
	}
	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
}
