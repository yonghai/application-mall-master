package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.NoticeCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class NoticeVo extends PageDto {
	
	private NoticeCustom noticeCustom;

	public NoticeCustom getNoticeCustom() {
		return noticeCustom;
	}

	public void setNoticeCustom(NoticeCustom noticeCustom) {
		this.noticeCustom = noticeCustom;
	}

	public NoticeVo(NoticeCustom noticeCustom) {
		super();
		this.noticeCustom = noticeCustom;
	}

	public NoticeVo() {
		super();
	}
	
}
