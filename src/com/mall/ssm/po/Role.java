package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 系统角色
 * @author Administrator
 *
 */
public class Role extends PageDto{
	/*
	 * 角色id
	 */
	private Integer id;
	/*
	 * 角色名
	 */
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
