package com.mall.ssm.po.custom;

import com.mall.ssm.po.dto.CityDto;
import com.mall.ssm.po.dto.CountyDto;
import com.mall.ssm.po.dto.ProvinceDto;

/**
 * Bean信息的扩展类
 * @author Administrator
 *
 */
public class ShopAddressCustom extends com.mall.ssm.po.ShopAddress{
	//添加信息的扩展属性
	private CountyDto county;
	private CityDto city;
	private ProvinceDto province;
	public CountyDto getCounty() {
		return county;
	}
	public void setCounty(CountyDto county) {
		this.county = county;
	}
	public CityDto getCity() {
		return city;
	}
	public void setCity(CityDto city) {
		this.city = city;
	}
	public ProvinceDto getProvince() {
		return province;
	}
	public void setProvince(ProvinceDto province) {
		this.province = province;
	}
	
}