package com.mall.ssm.po.result;

public class Keyword {
	private String value;
	private String color = "#173177";
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Keyword(String value) {
		super();
		this.value = value;
	}
	public Keyword() {
		super();
	}
	
}
