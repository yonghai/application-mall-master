package com.mall.ssm.mapper.coupons;

import com.mall.ssm.base.BaseDao;
import com.mall.ssm.po.custom.CouponsCustom;
import com.mall.ssm.po.vo.CouponsVo;

/**
 * 公告类操作
 * @author Administrator
 *
 */
public interface CouponsMapper extends BaseDao<CouponsVo,CouponsCustom>{

	public void insertCoupons(CouponsVo couponsVo);
	public void updateCouponsPublished(CouponsVo couponsVo);
	public java.util.List<CouponsCustom> getCouponsPublished(CouponsVo couponsVo);
	public CouponsCustom getCouponsPublishedById(CouponsVo couponsVo);
	public java.util.List<CouponsCustom> getFetchListByPage(CouponsVo couponsVo);
	public void deleteCouponsAll(String flag);
}
