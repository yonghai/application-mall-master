package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.PropertyItemCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class PropertyItemVo extends PageDto {
	private PropertyItemCustom propertyItemCustom;

	public PropertyItemCustom getPropertyItemCustom() {
		return propertyItemCustom;
	}

	public void setPropertyItemCustom(PropertyItemCustom propertyItemCustom) {
		this.propertyItemCustom = propertyItemCustom;
	}

	public PropertyItemVo(PropertyItemCustom propertyItemCustom) {
		super();
		this.propertyItemCustom = propertyItemCustom;
	}

	public PropertyItemVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
