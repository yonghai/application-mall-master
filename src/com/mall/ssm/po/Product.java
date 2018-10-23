package com.mall.ssm.po;

import com.mall.ssm.po.dto.PageDto;

/**
 * 产品  商品
 * @author Administrator
 *
 */
public class Product extends PageDto{
	/*
	 * 商品名称
	 */
	private String name;
	/*
	 * 图片
	 */
	private String pic;
	/*
	 * 商品所属分类
	 */
	private String cid;
	/*
	 * 商品价格
	 */
	private Double minPrice;
	/*
	 * 商品价格
	 */
	private Double originalPrice;
	/*
	 * 商品积分
	 */
	private Integer commission;
	
	/*
	 * 积分类型
	 */
	private Integer commissionType;
	/*
	 * 库存
	 */
	private Integer stores;
	/*
	 * 属性id
	 */
	private String propertyIds;
	
	/*---------------------------------------------*/
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String name, String pic, String cid, Double minPrice,
			Double originalPrice, Integer commission, 
			Integer stores, String propertyIds) {
		super();
		this.name = name;
		this.pic = pic;
		this.cid = cid;
		this.minPrice = minPrice;
		this.originalPrice = originalPrice;
		this.commission = commission;
		this.stores = stores;
		this.propertyIds = propertyIds;
	}
	/*
	 * 是否发布
	 */
	private Integer published;
	/*
	 * 排序
	 */
	private Integer orderBy;
	/*
	 * 保留字段,用户id
	 */
	private String uid;
	/*
	 * 商品添加时间
	 */
	private String dateAdd;
	
	/*
	 * 商品修改时间
	 */
	private String dateUpdate;
	
	/*
	 * 商品编号
	 */
	private Integer id;
	
	/*
	 * 商品评分
	 */
	private Double minScore;
	
	/*
	 * 喜欢人数
	 */
	private Integer numberFav;
	
	/*
	 * 好评人数
	 */
	private Integer numberGoodReputation;
	
	/*
	 * 订单数
	 */
	private Integer numberOrders;
	
	/*
	 * 浏览人数
	 */
	private Integer view;

	
	
	/*------------------------------------------------------------------------------*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public Integer getPublished() {
		return published;
	}

	public void setPublished(Integer published) {
		this.published = published;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getCommission() {
		return commission;
	}

	public void setCommission(Integer commission) {
		this.commission = commission;
	}

	public Integer getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(Integer commissionType) {
		this.commissionType = commissionType;
	}

	public Integer getStores() {
		return stores;
	}

	public void setStores(Integer stores) {
		this.stores = stores;
	}

	public String getPropertyIds() {
		return propertyIds;
	}

	public void setPropertyIds(String propertyIds) {
		this.propertyIds = propertyIds;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDateAdd() {
		return dateAdd;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public void setDateAdd(String dateAdd) {
		this.dateAdd = dateAdd;
	}

	public String getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMinScore() {
		return minScore;
	}

	public void setMinScore(Double minScore) {
		this.minScore = minScore;
	}

	public Integer getNumberFav() {
		return numberFav;
	}

	public void setNumberFav(Integer numberFav) {
		this.numberFav = numberFav;
	}

	public Integer getNumberGoodReputation() {
		return numberGoodReputation;
	}

	public void setNumberGoodReputation(Integer numberGoodReputation) {
		this.numberGoodReputation = numberGoodReputation;
	}

	public Integer getNumberOrders() {
		return numberOrders;
	}

	public void setNumberOrders(Integer numberOrders) {
		this.numberOrders = numberOrders;
	}

	public Integer getView() {
		return view;
	}

	public void setView(Integer view) {
		this.view = view;
	}
	
}
