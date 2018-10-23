package com.mall.ssm.util;

/**
 * 配置项
 * @author Administrator
 *
 */
public class Configuration {
	/**
	 * 图片默认保存路径/images
	 */
	public static final String DEFAULT_IMAGE_PATH="upload/images";//图片库
	public static final String DEFAULT_PHOTOS_PATH="upload/photos";//图片库
	public static final String DEFAULT_THUMB_PATH="upload/thumb";//图片库略缩图
	public static final String SWIPER_PATH="upload/swiper";//轮播图
	public static final String SWIPER_THUMB_PATH="upload/thumb";//轮播图略缩图
	public static final String TEMP_PATH="upload/temp";//临时文件
	public static final long IMAGE_MAX_SIZE= 1024*1024*10;//10M
	public static final String PRODUCT_PATH="upload/product";//产品图
	
	public static final int CaoGao = 0;//草稿
	public static final int ShangXian = 1;//已上线
	public static final int XiaJia = 2;//已下架
	
	
	public static String BaseUrl = "";
	public static String BaseImgDir = "";
}
