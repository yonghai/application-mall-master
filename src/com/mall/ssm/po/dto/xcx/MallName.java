package com.mall.ssm.po.dto.xcx;

public class MallName {
	private String createAt;
	private String key;
	private String updateAt;
	private int userid;
	private String value;
	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public MallName() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MallName(String createAt, String key, String updateAt, int userid,
			String value) {
		super();
		this.createAt = createAt;
		this.key = key;
		this.updateAt = updateAt;
		this.userid = userid;
		this.value = value;
	}
	public MallName(String value) {
		super();
		this.value = value;
	}
	
}
