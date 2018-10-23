package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.PhotoCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class PhotoVo extends PageDto {
	private PhotoCustom photoCustom;

	@Override
	public Integer getId() {
		return photoCustom.getId();
	}

	public PhotoVo(PhotoCustom photoCustom) {
		super();
		this.photoCustom = photoCustom;
	}



	public PhotoCustom getPhotoCustom() {
		return photoCustom;
	}



	public void setPhotoCustom(PhotoCustom photoCustom) {
		this.photoCustom = photoCustom;
	}



	public PhotoVo() {
		super();
	}
	
}
