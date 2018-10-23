package com.mall.ssm.po.dto.xcx;

/**
 * 返回结果封装
 * @author Administrator
 *
 */
public class R<T> {
	private int code;//返回码
	private T data;//返回的数据
	private String msg;//返回的消息
	public R(int code, T data, String msg) {
		super();
		this.code = code;
		this.data = data;
		this.msg = msg;
	}
	public R(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public R() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}