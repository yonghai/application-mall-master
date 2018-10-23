package com.mall.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mall.ssm.service.redis.Redis;


@Controller
public class RedisTestController {
	
	@Autowired
	private Redis<Object> redis;	
	
	@RequestMapping(value="/redis/save")
	public String test(String token)
		throws Exception {
		
		redis.add("test", "xx",1);
		long Expire = redis.getRedisTemplate().getExpire("test");
		System.out.println(Expire);
		String test = redis.get("d");
		System.out.println(test);
		return null;
	}
	
}
