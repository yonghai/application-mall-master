package com.mall.ssm.po.dto;

/**
 * 文件图片上传结果返回
 * @author Administrator
 *
 */
public class UploadImageMsg {
	private String url;//图片地址
	private String name;//图片文件名
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UploadImageMsg() {
		super();
	}
	public UploadImageMsg(String url, String name) {
		super();
		this.url = url;
		this.name = name;
	}
	
}
