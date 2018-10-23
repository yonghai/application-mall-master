package com.mall.ssm.po.dto;
/**
 * 文件上传返回的信息
 * @author Administrator
 *
 */
public class UploadMsg {
	/**
	 * code为上传状态，1为成功，0为失败
	 */
	private int code;
	/**
	 * msg为成功的文件路径或失败原因提示
	 */
	private Object msg;
	public UploadMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UploadMsg(int code, Object msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	
}
