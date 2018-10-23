package com.mall.ssm.po.dto.reputation;

import java.util.List;

public class ReputationDto {
	private String orderId;
	private String token;
	private List<Reputation2> reputations;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public List<Reputation2> getReputations() {
		return reputations;
	}
	public void setReputations(List<Reputation2> reputations) {
		this.reputations = reputations;
	}
	public ReputationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReputationDto(String orderId, String token,
			List<Reputation2> reputations) {
		super();
		this.orderId = orderId;
		this.token = token;
		this.reputations = reputations;
	}
	
}
