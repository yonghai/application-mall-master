package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 系统用户
 * @author Administrator
 *
 */
public class User extends PageDto{
	/*
	 * 用户id
	 */
	private Integer id;
	/*
	 * 用户登录昵称
	 */
	private String loginName;
	/*
	 * 用户登录密码
	 */
	private String pwd;
	/*
	 * 用户头像
	 */
	private String img;
	/*
	 * 用户邮箱
	 */
	private String email;
	/*
	 * 用户性别
	 */
	private int sex;
	/*
	 * 用户手机号
	 */
	private String mobile;
	/*
	 * 用户状态
	 * 0:未激活
	 * 1：正常
	 * 2：被禁用
	 */
	private int status;
	/*
	 * 用户真实姓名
	 */
	private String name;
	/*
	 * 用户角色id
	 */
	private int roleId;
	/*
	 * 用户激活码
	 */
	private String activationCode;
	/*
	 * 用户微信绑定id
	 */
	private String appid;
	
	
	public User(int id, String loginName, String pwd) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.pwd = pwd;
	}
	public User(String loginName, String pwd) {
		super();
		this.loginName = loginName;
		this.pwd = pwd;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String loginName, String pwd, String img, String email,
			int sex, String mobile, int status, String name, int roleId,
			String activationCode, String appid) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.pwd = pwd;
		this.img = img;
		this.email = email;
		this.sex = sex;
		this.mobile = mobile;
		this.status = status;
		this.name = name;
		this.roleId = roleId;
		this.activationCode = activationCode;
		this.appid = appid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	
}
