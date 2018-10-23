package com.mall.ssm.po.dto;

import java.io.Serializable;

/**
 * 省
 * @author Administrator
 *
 */
public class ProvinceDto implements Serializable{
	private String name;
	private Integer id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProvinceDto(String name, Integer id) {
		super();
		this.name = name;
		this.id = id;
	}
	public ProvinceDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
