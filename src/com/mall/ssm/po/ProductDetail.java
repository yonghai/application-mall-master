package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 商品详情
 * @author Administrator
 *
 */
public class ProductDetail extends PageDto{
	/*
	 * 商品编号
	 */	
	private Integer id;
	/*
	 * 图片1
	 */
	private String pic1;
	/*
	 * 图片2
	 */
	private String pic2;
	/*
	 * 图片3
	 */
	private String pic3;
	/*
	 * html详细介绍
	 */
	private String content;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPic1() {
		return pic1;
	}
	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}
	public String getPic2() {
		return pic2;
	}
	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}
	public String getPic3() {
		return pic3;
	}
	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ProductDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDetail(Integer id, String pic1, String pic2, String pic3,
			String content) {
		super();
		this.id = id;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.content = content;
	}
	
}
