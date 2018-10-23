package com.mall.ssm.service.user;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.user.UserMapper;
import com.mall.ssm.po.custom.UserCustom;
import com.mall.ssm.po.vo.UserVo;

@Service
public class UserService extends CrudService<UserMapper, UserVo,UserCustom>{

}
