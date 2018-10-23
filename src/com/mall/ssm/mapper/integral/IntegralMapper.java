package com.mall.ssm.mapper.integral;

import java.util.List;

import com.mall.ssm.po.custom.IntegralCustom;
import com.mall.ssm.po.custom.IntegralDetailCustom;
import com.mall.ssm.po.vo.IntegralDetailVo;
import com.mall.ssm.po.vo.IntegralVo;

/**
 * 积分类操作
 * @author Administrator
 *
 */
public interface IntegralMapper{
	
	public IntegralCustom getIntegralById(String id);
	
	public IntegralCustom getIntegral(IntegralVo integralVo);

	public List<IntegralCustom> getIntegralListByPage(IntegralVo integralVo);

	public void insertIntegral(IntegralVo integralVo);

	public void updateIntegral(IntegralVo integralVo);
	
	public void deleteIntegral(String id);
	
	public List<IntegralDetailCustom> getIntegralDetailListByUserId(String appid);
	
	public void insertIntegralDetail(IntegralDetailVo integralDetailVo);
	
	public void updateIntegralDetail(IntegralDetailVo integralDetailVo);
	
	public void deleteIntegralDetail(String appid);

	public IntegralDetailCustom getLaseIntegralDetail(String appid);

}
