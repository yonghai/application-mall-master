package com.mall.ssm.po.dto.reputation;

public class Reputation2 {
	private Integer id;
	private String remark;
	private Integer reputation;
	private Integer goodsId;
	
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getReputation() {
		return reputation;
	}
	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}
	
	public Reputation2(Integer id, String remark, Integer reputation,
			Integer goodsId) {
		super();
		this.id = id;
		this.remark = remark;
		this.reputation = reputation;
		this.goodsId = goodsId;
	}
	public Reputation2() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
