package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.UserCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class UserVo extends PageDto {
	private UserCustom userCustom;
	@Override
	public Integer getId() {
		return userCustom.getId();
	}
	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}

	public UserVo(UserCustom userCustom) {
		super();
		this.userCustom = userCustom;
	}

	public UserVo() {
		super();
	}
	
}
