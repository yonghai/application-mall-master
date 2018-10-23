package com.mall.ssm.po.custom;

import java.util.List;

/**
 * Bean信息的扩展类
 * @author Administrator
 *
 */
public class PropertyCustom extends com.mall.ssm.po.Property{
	//添加信息的扩展属性
	private List<PropertyItemCustom> propertyItemCustoms;

	public List<PropertyItemCustom> getPropertyItemCustoms() {
		return propertyItemCustoms;
	}

	public void setPropertyItemCustoms(List<PropertyItemCustom> propertyItemCustoms) {
		this.propertyItemCustoms = propertyItemCustoms;
	}

	
}
