package com.mall.ssm.po;

import java.io.Serializable;
import java.util.List;

/**
 * 省
 * @author Administrator
 *
 */
public class Province implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer id;
	private List<City> cityList;
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
	public List<City> getCityList() {
		return cityList;
	}
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
	public Province(String name, Integer id, List<City> cityList) {
		super();
		this.name = name;
		this.id = id;
		this.cityList = cityList;
	}
	public Province() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
