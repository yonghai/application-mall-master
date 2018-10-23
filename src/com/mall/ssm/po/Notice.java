package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 公告类
 * @author Administrator
 *
 */
public class Notice extends PageDto{
	/**
	 * 所属用户id
	 */
	private String uid;
	/**
	 * 公告id
	 */
	private Integer id;
	/**
	 * 公告内容
	 */
	private String content;
	/**
	 * 更新时间
	 */
	private String updatetime;
	/**
	 * 公告标题
	 */
	private String title;
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
	public Notice(String uid, int id, String content,
			String updatetime, int published) {
		super();
		this.uid = uid;
		this.id = id;
		this.content = content;
		this.updatetime = updatetime;
		this.published = published;
	}
	
	public Notice(String uid, int id, String content,
			String updatetime, int published, String title) {
		super();
		this.uid = uid;
		this.id = id;
		this.content = content;
		this.updatetime = updatetime;
		this.title = title;
		this.published = published;
	}

	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the ownerid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param ownerid the ownerid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	
}
