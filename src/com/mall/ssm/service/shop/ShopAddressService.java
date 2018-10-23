package com.mall.ssm.service.shop;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.shop.ShopAddressMapper;
import com.mall.ssm.po.custom.ShopAddressCustom;
import com.mall.ssm.po.vo.ShopAddressVo;

@Service
public class ShopAddressService extends CrudService<ShopAddressMapper, ShopAddressVo,ShopAddressCustom>{
	/**
	 * 更新默认收货地址
	 * @param entity
	 */
	public void updateDefaultAddress(ShopAddressVo entity) {
		((ShopAddressMapper)super.dao).updateDefaultAddress(entity);
	}
}
