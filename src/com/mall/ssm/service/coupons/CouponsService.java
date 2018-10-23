package com.mall.ssm.service.coupons;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.coupons.CouponsMapper;
import com.mall.ssm.po.custom.CouponsCustom;
import com.mall.ssm.po.vo.CouponsVo;

@Service
public class CouponsService extends CrudService<CouponsMapper, CouponsVo,CouponsCustom>{
	/**
	 * 发行优惠券
	 * @param couponsVo
	 */
	public void insertCoupons(CouponsVo couponsVo){
		((CouponsMapper)super.dao).insertCoupons(couponsVo);
	}
	
	public List<CouponsCustom> getCouponsPublished(CouponsVo couponsVo){
		return ((CouponsMapper)super.dao).getCouponsPublished(couponsVo);
	}
	public void updateCouponsPublished(CouponsVo couponsVo){
		((CouponsMapper)super.dao).updateCouponsPublished(couponsVo);
	}
	public CouponsCustom getCouponsPublishedById(CouponsVo couponsVo){
		return ((CouponsMapper)super.dao).getCouponsPublishedById(couponsVo);
	}
	public List<CouponsCustom> getFetchListByPage(CouponsVo couponsVo){
		return ((CouponsMapper)super.dao).getFetchListByPage(couponsVo);
	}
	public void deleteCouponsAll(String flag){
		((CouponsMapper)super.dao).deleteCouponsAll(flag);
	}
	
}
