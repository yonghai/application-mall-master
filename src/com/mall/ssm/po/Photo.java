package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 相册图片
 * @author Administrator
 * 
 		tb_photo数据表的结构
字段名			  数据类型			默认值	描述
id				int	            	NULL	系统自动编号
photoName		varchar(50)			NULL	相片名称
photoSize		varchar(50)			NULL	相片大小
photoType		varchar(50)			NULL	相片类型
photoTime		varchar(50)			NULL	相片上传时间
photoAddress	varchar(50)			NULL	相片地址
username		varchar(50)			NULL	相片上传用户名
smallPhoto		varchar(50)			NULL	相片缩略图地址
 */
public class Photo extends PageDto{
	private Integer id;//系统自动编号
	private String photoName;//相片名称
	private String photoSize;//相片大小
	private String photoType="0";//相片类型
	private String photoTime;//相片上传时间
	private String photoAddress;//相片地址
	private String username;//	相片上传用户名
	private String smallPhoto;//相片缩略图地址

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoSize() {
		return photoSize;
	}
	public void setPhotoSize(String photoSize) {
		this.photoSize = photoSize;
	}
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	public String getPhotoTime() {
		return photoTime;
	}
	public void setPhotoTime(String photoTime) {
		this.photoTime = photoTime;
	}
	public String getPhotoAddress() {
		return photoAddress;
	}
	public void setPhotoAddress(String photoAddress) {
		this.photoAddress = photoAddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSmallPhoto() {
		return smallPhoto;
	}
	public void setSmallPhoto(String smallPhoto) {
		this.smallPhoto = smallPhoto;
	}
	public Photo(int id, String photoName, String photoSize,
			String photoType, String photoTime, String photoAddress,
			String username, String smallPhoto) {
		super();
		this.id = id;
		this.photoName = photoName;
		this.photoSize = photoSize;
		this.photoType = photoType;
		this.photoTime = photoTime;
		this.photoAddress = photoAddress;
		this.username = username;
		this.smallPhoto = smallPhoto;
	}
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
