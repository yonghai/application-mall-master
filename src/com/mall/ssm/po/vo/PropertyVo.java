package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.PropertyCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class PropertyVo extends PageDto {
	private PropertyCustom propertyCustom;

	public PropertyCustom getPropertyCustom() {
		return propertyCustom;
	}

	public void setPropertyCustom(PropertyCustom propertyCustom) {
		this.propertyCustom = propertyCustom;
	}

	public PropertyVo(PropertyCustom propertyCustom) {
		super();
		this.propertyCustom = propertyCustom;
	}

	public PropertyVo() {
		super();
	}
	
}
