package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 产品类别
 * @author Administrator
 *
 */
public class ProductCategory extends PageDto{
	private Integer id;//产品分类id
	private String uid;//用户id,保留字段
	private String name;//产品类名
	private Integer orderBy;//产品排序号
	public ProductCategory(int id, String uid, String name,int orderBy) {
		super();
		this.id = id;
		this.uid = uid;
		this.name = name;
		this.orderBy = orderBy;
	}
	public ProductCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
	
}
