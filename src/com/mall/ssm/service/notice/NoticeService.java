package com.mall.ssm.service.notice;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.notice.NoticeMapper;
import com.mall.ssm.po.custom.NoticeCustom;
import com.mall.ssm.po.vo.NoticeVo;

@Service
public class NoticeService extends CrudService<NoticeMapper, NoticeVo,NoticeCustom>{

}
