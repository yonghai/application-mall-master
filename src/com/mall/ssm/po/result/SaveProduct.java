package com.mall.ssm.po.result;

/**
 * 新增商品、产品返回值
 * @author Administrator
 *
 */
public class SaveProduct {
	private String msg;
	private String url;
	private String thumb;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public SaveProduct(String msg, String url, String thumb) {
		super();
		this.msg = msg;
		this.url = url;
		this.thumb = thumb;
	}
	public SaveProduct() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
