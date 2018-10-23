package com.mall.ssm.po.dto;

import java.io.Serializable;

/**
 * 市
 * @author Administrator
 *
 */
public class CityDto implements Serializable{
	private Integer id;
	private Integer pid;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CityDto(Integer id, Integer pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}
	public CityDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
