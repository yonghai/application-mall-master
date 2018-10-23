package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.IntegralCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class IntegralVo extends PageDto {
	
	private IntegralCustom integralCustom;

	public IntegralCustom getIntegralCustom() {
		return integralCustom;
	}

	public void setIntegralCustom(IntegralCustom integralCustom) {
		this.integralCustom = integralCustom;
	}
	
	
}
