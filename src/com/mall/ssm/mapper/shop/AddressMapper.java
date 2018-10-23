package com.mall.ssm.mapper.shop;

import com.mall.ssm.po.dto.CityDto;
import com.mall.ssm.po.dto.CountyDto;
import com.mall.ssm.po.dto.ProvinceDto;

/**
 * 收货地址操作
 * @author Administrator
 *
 */
public interface AddressMapper{
	
	public ProvinceDto getProvince(Integer id);
	public CityDto getCity(Integer id);
	public CountyDto getCounty(Integer id);
}
