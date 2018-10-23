package com.mall.ssm.mapper.property;

import com.mall.ssm.base.BaseDao;
import com.mall.ssm.po.custom.PropertyCustom;
import com.mall.ssm.po.vo.PropertyVo;

/**
 * 属性操作
 * @author Administrator
 *
 */
public interface PropertyMapper extends BaseDao<PropertyVo,PropertyCustom>{
	/**
	 * 延迟加载
	 * @param entitys
	 */
	public PropertyCustom getFull(PropertyVo propertyVo);
}
