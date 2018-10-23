package com.mall.ssm.po;

import java.io.Serializable;

import com.mall.ssm.po.dto.PageDto;

/**
 * 优惠券
 * @author Administrator
 *
 */
public class Coupons extends PageDto implements Serializable{
	private Integer id;//优惠券id
	private Integer money;//优惠金额
	private String name;//优惠券名称
	private Integer moneyHreshold;//满X元使用
	private String dateEnd;//有效期
	private String openid;//用户id,用微信的id代替
	private Integer status;//装态
	private Integer type;//类型
	private String flag;//券别标志

	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMoneyHreshold() {
		return moneyHreshold;
	}
	public void setMoneyHreshold(Integer moneyHreshold) {
		this.moneyHreshold = moneyHreshold;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Coupons() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Coupons(Integer id, Integer money, String name,Integer moneyHreshold,String flag) {
		super();
		this.id = id;
		this.money = money;
		this.name = name;
		this.moneyHreshold = moneyHreshold;
		this.flag = flag;
	}
	
}
