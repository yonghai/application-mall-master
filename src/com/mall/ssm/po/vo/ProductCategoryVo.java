package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.ProductCategoryCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class ProductCategoryVo extends PageDto {
	private ProductCategoryCustom productCategoryCustom;

	public ProductCategoryCustom getProductCategoryCustom() {
		return productCategoryCustom;
	}

	public void setProductCategoryCustom(ProductCategoryCustom productCategoryCustom) {
		this.productCategoryCustom = productCategoryCustom;
	}

	public ProductCategoryVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductCategoryVo(ProductCategoryCustom productCategoryCustom) {
		super();
		this.productCategoryCustom = productCategoryCustom;
	}
	
}
