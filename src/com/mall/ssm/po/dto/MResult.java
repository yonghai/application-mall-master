package com.mall.ssm.po.dto;

/**
 * 结果封装
 * @author Administrator
 *
 */
public class MResult {
	private boolean ok;//返回是否成功
	private Object result;//成功返回数据,失败返回失败原因
	/**
	 * @return the ok
	 */
	public boolean isOk() {
		return ok;
	}
	/**
	 * @param ok the ok to set
	 */
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}
	public MResult(boolean ok, Object result) {
		super();
		this.ok = ok;
		this.result = result;
	}
	public MResult() {
		super();
	}
	
}
