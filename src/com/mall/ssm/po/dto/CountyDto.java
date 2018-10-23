package com.mall.ssm.po.dto;

import java.io.Serializable;

/**
 * 县
 * @author Administrator
 *
 */
public class CountyDto implements Serializable{
	private Integer id;//id
	private Integer pid;//市id
	private String name;//名称
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
	public CountyDto(Integer id, Integer pid, String name) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
	}
	public CountyDto() {
		super();
	}
	
}
