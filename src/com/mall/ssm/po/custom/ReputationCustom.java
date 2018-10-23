package com.mall.ssm.po.custom;
/**
 * Bean信息的扩展类
 * @author Administrator
 *
 */
public class ReputationCustom extends com.mall.ssm.po.Reputation{
	//添加信息的扩展属性
	private WxUserCustom user;
	private ProductCustom productCustom;

	
	

	public ProductCustom getProductCustom() {
		return productCustom;
	}

	public void setProductCustom(ProductCustom productCustom) {
		this.productCustom = productCustom;
	}

	public WxUserCustom getUser() {
		return user;
	}

	public void setUser(WxUserCustom user) {
		this.user = user;
	}
	
}
