package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.SwiperCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class SwiperVo extends PageDto {
	private SwiperCustom swiperCustom;
	private String fileName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public Integer getId() {
		return swiperCustom.getId();
	}
	public SwiperCustom getSwiperCustom() {
		return swiperCustom;
	}
	public void setSwiperCustom(SwiperCustom swiperCustom) {
		this.swiperCustom = swiperCustom;
	}
	public SwiperVo(SwiperCustom swiperCustom) {
		super();
		this.swiperCustom = swiperCustom;
	}
	public SwiperVo() {
		super();
	}
	
}
