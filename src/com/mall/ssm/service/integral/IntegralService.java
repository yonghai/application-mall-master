package com.mall.ssm.service.integral;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.ssm.mapper.integral.IntegralMapper;
import com.mall.ssm.po.custom.IntegralCustom;
import com.mall.ssm.po.custom.IntegralDetailCustom;
import com.mall.ssm.po.vo.IntegralDetailVo;
import com.mall.ssm.po.vo.IntegralVo;

@Service
public class IntegralService{
	
	private IntegralMapper dao;
	
	@Autowired
	public void setDao(IntegralMapper dao) {
		this.dao = dao;
	}
	
	public void deleteIntegral(String id) {
		dao.deleteIntegral(id);
	}
	public void deleteIntegralDetail(String appid) {
		dao.deleteIntegralDetail(appid);
	}
	public IntegralCustom getIntegralById(String id) {
		return dao.getIntegralById(id);
	}
	public IntegralCustom getIntegral(IntegralVo integralVo) {
		return dao.getIntegral(integralVo);
	}
	public List<IntegralDetailCustom> getIntegralDetailListByUserId(String appid) {
		return dao.getIntegralDetailListByUserId(appid);
	}
	public List<IntegralCustom> getIntegralListByPage(IntegralVo integralVo) {
		return dao.getIntegralListByPage(integralVo);
	}
	public void insertIntegral(IntegralVo integralVo) {
		dao.insertIntegral(integralVo);
	}
	public void insertIntegralDetail(IntegralDetailVo integralDetailVo) {
		dao.insertIntegralDetail(integralDetailVo);
	}
	public void updateIntegral(IntegralVo integralVo) {
		dao.updateIntegral(integralVo);
	}
	public void updateIntegralDetail(IntegralDetailVo integralDetailVo) {
		dao.updateIntegralDetail(integralDetailVo);
	}
	public IntegralDetailCustom getLaseIntegralDetail(String appid){
		return dao.getLaseIntegralDetail(appid);
	}
}
