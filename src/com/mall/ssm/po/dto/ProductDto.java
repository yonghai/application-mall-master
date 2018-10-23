package com.mall.ssm.po.dto;

import java.util.ArrayList;
import java.util.List;

import com.mall.ssm.po.custom.ProductCategoryCustom;
import com.mall.ssm.po.custom.ProductCustom;
import com.mall.ssm.po.custom.PropertyCustom;

/**
 * 商品产品
 * @author Administrator
 *
 */
public class ProductDto {
	private List<ProductCategoryCustom> categorys = new ArrayList<ProductCategoryCustom>();
	private List<PropertyCustom> properties = new ArrayList<PropertyCustom>();
	private ProductCustom basicInfo;
	private List<String> pics = new ArrayList<String>();
	private String content;
	public List<ProductCategoryCustom> getCategorys() {
		return categorys;
	}
	public void setCategorys(List<ProductCategoryCustom> categorys) {
		this.categorys = categorys;
	}

	public List<PropertyCustom> getProperties() {
		return properties;
	}
	public void setProperties(List<PropertyCustom> properties) {
		this.properties = properties;
	}
	public ProductCustom getBasicInfo() {
		return basicInfo;
	}
	public void setBasicInfo(ProductCustom basicInfo) {
		this.basicInfo = basicInfo;
	}
	public List<String> getPics() {
		return pics;
	}
	public void setPics(List<String> pics) {
		this.pics = pics;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
