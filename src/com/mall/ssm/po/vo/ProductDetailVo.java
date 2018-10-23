package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.ProductDetailCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class ProductDetailVo extends PageDto {
	private ProductDetailCustom productDetailCustom;

	public ProductDetailCustom getProductDetailCustom() {
		return productDetailCustom;
	}

	public void setProductDetailCustom(ProductDetailCustom productDetailCustom) {
		this.productDetailCustom = productDetailCustom;
	}

	public ProductDetailVo() {
		super();
	}
	
}
