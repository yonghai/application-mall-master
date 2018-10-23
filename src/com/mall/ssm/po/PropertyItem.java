package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 具体属性
 * @author Administrator
 *
 */
public class PropertyItem extends PageDto{
	private Integer id;//属性id
	private String name;//属性名
	private Integer orderBy;//排序
	private String updateTime;//更新时间
	private Integer propertyId;//属性类id
	public PropertyItem(int id, String name, int orderBy, String updateTime,
			int propertyId) {
		super();
		this.id = id;
		this.name = name;
		this.orderBy = orderBy;
		this.updateTime = updateTime;
		this.propertyId = propertyId;
	}
	public PropertyItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	
}
