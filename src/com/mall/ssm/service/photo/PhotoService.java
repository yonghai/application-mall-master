package com.mall.ssm.service.photo;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.photo.PhotoMapper;
import com.mall.ssm.po.custom.PhotoCustom;
import com.mall.ssm.po.vo.PhotoVo;

@Service
public class PhotoService extends CrudService<PhotoMapper, PhotoVo,PhotoCustom>{

}
