package com.mall.ssm.po;

import java.io.Serializable;
import java.util.List;

/**
 * 市
 * @author Administrator
 *
 */
public class City implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer pid;
	private String name;
	private List<County> districtList;
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
	public List<County> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<County> districtList) {
		this.districtList = districtList;
	}
	public City(Integer id, Integer pid, String name, List<County> districtList) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.districtList = districtList;
	}
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
