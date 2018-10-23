package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.CouponsCustom;
import com.mall.ssm.po.dto.PageDto;
/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class CouponsVo extends PageDto {
	private CouponsCustom couponsCustom;

	public CouponsCustom getCouponsCustom() {
		return couponsCustom;
	}

	public void setCouponsCustom(CouponsCustom couponsCustom) {
		this.couponsCustom = couponsCustom;
	}
	
}
