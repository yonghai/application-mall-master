package com.mall.ssm.po.custom;

/**
 * Bean信息的扩展类
 * @author Administrator
 *
 */
public class CouponsCustom extends com.mall.ssm.po.Coupons{
	//添加信息的扩展属性
	
	private Integer stores;
	private Integer remain;//剩余数量,redis中取得
	
	private WxUserCustom wxUserCustom;
	
	public Integer getRemain() {
		return remain;
	}

	public void setRemain(Integer remain) {
		this.remain = remain;
	}

	public Integer getStores() {
		return stores;
	}

	public void setStores(Integer stores) {
		this.stores = stores;
	}

	public WxUserCustom getWxUserCustom() {
		return wxUserCustom;
	}

	public void setWxUserCustom(WxUserCustom wxUserCustom) {
		this.wxUserCustom = wxUserCustom;
	}

}
