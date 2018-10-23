package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.IntegralDetailCustom;
import com.mall.ssm.po.custom.OrderItemCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class IntegralDetailVo extends PageDto {
	
	private IntegralDetailCustom integralDetailCustom;

	public IntegralDetailCustom getIntegralDetailCustom() {
		return integralDetailCustom;
	}

	public void setIntegralDetailCustom(IntegralDetailCustom integralDetailCustom) {
		this.integralDetailCustom = integralDetailCustom;
	}


	
	
}
