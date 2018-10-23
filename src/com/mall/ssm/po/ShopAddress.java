package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 收货地址
 * @author Administrator
 *
 */
public class ShopAddress extends PageDto{
	private Integer id;//id
	private Integer provinceId;//省
	private Integer cityId;//市
	private Integer districtId;//县
	private String linkMan;//联系人
	private String address;//详细地址
	private String mobile;//联系方式
	private String code;//邮编
	private String appid;//appid
	private Boolean isDefault;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public ShopAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShopAddress(Integer id, Integer provinceId, Integer cityId, Integer districtId,
			String linkMan, String address, String mobile, String code,
			String appid,Boolean isDefault) {
		super();
		this.id = id;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
		this.linkMan = linkMan;
		this.address = address;
		this.mobile = mobile;
		this.code = code;
		this.appid = appid;
		this.isDefault = isDefault;
	}
	
}
