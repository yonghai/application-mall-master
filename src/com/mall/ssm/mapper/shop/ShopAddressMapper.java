package com.mall.ssm.mapper.shop;

import com.mall.ssm.base.BaseDao;
import com.mall.ssm.po.custom.ShopAddressCustom;
import com.mall.ssm.po.vo.ShopAddressVo;

/**
 * 收货地址操作
 * @author Administrator
 *
 */
public interface ShopAddressMapper extends BaseDao<ShopAddressVo,ShopAddressCustom>{
	/**
	 * 更新默认收货地址
	 * @param entity
	 */
	public void updateDefaultAddress(ShopAddressVo entity);
}
