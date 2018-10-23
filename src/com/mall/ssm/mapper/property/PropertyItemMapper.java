package com.mall.ssm.mapper.property;

import java.util.List;

import com.mall.ssm.base.BaseDao;
import com.mall.ssm.po.custom.PropertyItemCustom;
import com.mall.ssm.po.vo.PropertyItemVo;

/**
 * 属性Item操作
 * @author Administrator
 *
 */
public interface PropertyItemMapper extends BaseDao<PropertyItemVo,PropertyItemCustom>{
	
	/**
	 * 批量插入
	 * @param entitys
	 */
	public void inserBatch(List<PropertyItemVo> entitys);
	
	/**
	 * 根据propertyId删除
	 * @param id
	 * @return
	 */
	public void deleteByPropertyId(int id);
}
