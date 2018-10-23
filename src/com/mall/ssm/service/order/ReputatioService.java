package com.mall.ssm.service.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mall.ssm.base.CrudService;
import com.mall.ssm.mapper.order.ReputationMapper;
import com.mall.ssm.po.custom.ReputationCustom;
import com.mall.ssm.po.vo.ReputationVo;

@Service
public class ReputatioService extends CrudService<ReputationMapper, ReputationVo,ReputationCustom>{
	/**
	 * 批量插入评论
	 * @param entitys
	 */
	public void insertBatch(List<ReputationVo> list){
		((ReputationMapper)super.dao).insertBatch(list);
	}
}
