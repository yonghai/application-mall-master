package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.ShopAddressCustom;
import com.mall.ssm.po.dto.PageDto;
/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class ShopAddressVo extends PageDto {
	private ShopAddressCustom shopAddressCustom;

	private String token;

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public ShopAddressVo(ShopAddressCustom shopAddressCustom) {
		super();
		this.shopAddressCustom = shopAddressCustom;
	}



	public ShopAddressCustom getShopAddressCustom() {
		return shopAddressCustom;
	}



	public void setShopAddressCustom(ShopAddressCustom shopAddressCustom) {
		this.shopAddressCustom = shopAddressCustom;
	}



	public ShopAddressVo() {
		super();
	}
	
}
