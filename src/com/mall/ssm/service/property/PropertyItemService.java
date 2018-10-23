package com.mall.ssm.service.property;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.property.PropertyItemMapper;
import com.mall.ssm.po.custom.PropertyItemCustom;
import com.mall.ssm.po.vo.PropertyItemVo;

@Service
public class PropertyItemService extends CrudService<PropertyItemMapper, PropertyItemVo,PropertyItemCustom>{
	/**
	 * 批量插入
	 * @param entitys
	 */
	public void inserBatch(List<PropertyItemVo> entitys){
		((PropertyItemMapper)super.dao).inserBatch(entitys);
	}
	/**
	 * 根据propertyId删除
	 * @param id
	 * @return
	 */
	public void deleteByPropertyId(int id){
		((PropertyItemMapper)super.dao).deleteByPropertyId(id);
	}
}
