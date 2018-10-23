package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 轮播图
 * @author Administrator
 *
 */
public class Swiper extends PageDto{
	/**
	 * 所属用户id
	 */
	private String uid;
	/**
	 * 轮播图id
	 */
	private Integer id;
	
	/**
	 * 轮播图URL
	 */
	private String url;
	
	/**
	 * 轮播图略缩图
	 */
	private String thumb;
	
	/**
	 * 链接内容
	 */
	private String content;
	
	/**
	 * 更新时间
	 */
	private String updatetime;
	/**
	 * 是否发布
	 * 1.草稿
	 * 2.申请提交
	 * 3.申请成功
	 * 4.下架
	 */
	private Integer published;
	/**
	 * 类型:带链接1或不带0
	 */
	private Integer type=0;
	public Swiper() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public String getThumb() {
		return thumb;
	}


	public void setThumb(String thumb) {
		this.thumb = thumb;
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getPublished() {
		return published;
	}


	public void setPublished(Integer published) {
		this.published = published;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the updatetime
	 */
	public String getUpdatetime() {
		return updatetime;
	}

	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public Swiper(String uid, int id, String url, String thumb,
			String content, String updatetime, Integer published) {
		super();
		this.uid = uid;
		this.id = id;
		this.url = url;
		this.thumb = thumb;
		this.content = content;
		this.updatetime = updatetime;
		this.published = published;
	}
	
}
