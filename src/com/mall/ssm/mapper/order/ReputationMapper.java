package com.mall.ssm.mapper.order;

import java.util.List;

import com.mall.ssm.base.BaseDao;
import com.mall.ssm.po.custom.ReputationCustom;
import com.mall.ssm.po.vo.ReputationVo;

/**
 * 评论类操作
 * @author Administrator
 *
 */
public interface ReputationMapper extends BaseDao<ReputationVo,ReputationCustom>{
	/**
	 * 批量插入评论
	 * @param list
	 */
	public void insertBatch(List<ReputationVo> list);
}