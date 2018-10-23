package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 微信用户
 * @author Administrator
 *
 */
public class WxUser extends PageDto{
	private Integer id;
	private String appid;//对应的openid
	private String nickName;
	private String avatarUrl;
	private String gender;
	private String city;
	private String province;
	private String addtime;
	private Integer state;
	private String userMobile;
	
	
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public WxUser(Integer id, String appid, String nickName, String avatarUrl,
			String gender, String city, String province, String addtime,Integer state) {
		super();
		this.id = id;
		this.appid = appid;
		this.nickName = nickName;
		this.avatarUrl = avatarUrl;
		this.gender = gender;
		this.city = city;
		this.province = province;
		this.addtime = addtime;
		this.state = state;
	}
	public WxUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	
	
}
