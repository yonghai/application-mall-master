package com.mall.ssm.po.custom;

/**
 * Bean信息的扩展类
 * @author Administrator
 *
 */
public class IntegralDetailCustom extends com.mall.ssm.po.IntegralDetail{
	//添加信息的扩展属性
	private IntegralCustom integralCustom;

	public IntegralCustom getIntegralCustom() {
		return integralCustom;
	}

	public void setIntegralCustom(IntegralCustom integralCustom) {
		this.integralCustom = integralCustom;
	}

}
