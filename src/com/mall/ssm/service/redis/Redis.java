package com.mall.ssm.service.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.mall.ssm.base.RedisCrud;

/**
 * 
 * @author Administrator
 *
 */
@Service
@SuppressWarnings("unchecked")
public class Redis<T> implements RedisCrud<String,String, T>{
	@Resource(name="redisTemplate")
	private RedisTemplate redisTemplate;
	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}
	
	public boolean exist(String key) {
		if(redisTemplate==null){
	        throw new RuntimeException("RedisTemplate未实例化");
	    }else{
	       return redisTemplate.hasKey(key);
	    }
	}
	
	public void add(String key, String value) {
		if(redisTemplate==null){
	        throw new RuntimeException("RedisTemplate未实例化");
	    }else{
	       redisTemplate.opsForValue().set(key,value);
	    }
	}
	public void add(String key, String value,long minutes) {
		if(redisTemplate==null){
	        throw new RuntimeException("RedisTemplate未实例化");
	    }else{
	       redisTemplate.opsForValue().set(key,value,minutes,TimeUnit.MINUTES);
	    }
	}
	public void delete(String key) {
		if(redisTemplate==null){
	        throw new RuntimeException("RedisTemplate未实例化");
	    }else{
	       redisTemplate.delete(key);
	    }
	}
	
	public void addObj(String objectKey, String key, T object) {
		if(redisTemplate==null){
			 throw new RuntimeException("RedisTemplate未实例化");
        }else{
            redisTemplate.opsForHash().put(objectKey,key,object);
        }
	}
	
	public Set getObjs(String objectKey) {
		if(redisTemplate==null){
			 throw new RuntimeException("RedisTemplate未实例化");
        }else{
        	return redisTemplate.opsForHash().keys(objectKey);
        }
	}

	public void deletObj(String objectKey, String key) {
		if(redisTemplate==null){
            throw new RuntimeException("RedisTemplate未实例化");
        }else{
        	throw new RuntimeException("Not implement Yet!");
           //redisTemplate.delete(objectKey);
        }
	}

	public void delete(List<String> listKeys) {
		if(redisTemplate==null){
			 throw new RuntimeException("RedisTemplate未实例化");
       }else{
           redisTemplate.delete(listKeys);
       }
	}

	public String get(String key) {
		String value = (String) redisTemplate.opsForValue().get(key);
        return value;
	}

	public T getObj(String objectKey, String key) {
		T obj = (T) redisTemplate.opsForHash().get(objectKey,key);
        return obj;
	}

	public void update(String key, String value) {
		if(redisTemplate==null){
	        throw new RuntimeException("RedisTemplate未实例化");
	    }else{
	       redisTemplate.opsForValue().set(key,value);
	    }
	}

	public void updateObj(String objectKey, String key, T object) {
	   if(redisTemplate==null){
			 throw new RuntimeException("RedisTemplate未实例化");
       }else{
           redisTemplate.opsForHash().put(objectKey,key,object);
       }
	}

}
