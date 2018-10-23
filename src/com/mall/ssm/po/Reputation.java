package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 评论
 * @author Administrator
 *
 */
public class Reputation extends PageDto{
	private Integer id;
	private Integer goodsId;//商品id
	private Integer reputation;//分数 1 2 0
	private String remark;//评论
	private String appid;//openid
	private String dataAdd;//评论时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getReputation() {
		return reputation;
	}
	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getDataAdd() {
		return dataAdd;
	}
	public void setDataAdd(String dataAdd) {
		this.dataAdd = dataAdd;
	}
	public Reputation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
