package com.mall.ssm.service.property;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.property.PropertyMapper;
import com.mall.ssm.po.custom.PropertyCustom;
import com.mall.ssm.po.vo.PropertyVo;

@Service
public class PropertyService extends CrudService<PropertyMapper, PropertyVo,PropertyCustom>{
	/**
	 * 批量插入
	 * @param entitys
	 */
	public PropertyCustom getFull(PropertyVo propertyVo){
		return ((PropertyMapper)super.dao).getFull(propertyVo);
	}
}
