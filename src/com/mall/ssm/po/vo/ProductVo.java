package com.mall.ssm.po.vo;

import com.mall.ssm.po.custom.ProductCustom;
import com.mall.ssm.po.dto.PageDto;

/*
 * 为了系统可扩展性,对原始生成的po进行扩展
 */
public class ProductVo extends PageDto {
	private ProductCustom productCustom;
	
	private String content;
	private String pic1;
	private String pic2;
	private String pic3;
	
	private String oldPic;
	private String oldPic1;
	private String oldPic2;
	private String oldPic3;

	public ProductCustom getProductCustom() {
		return productCustom;
	}

	public void setProductCustom(ProductCustom productCustom) {
		this.productCustom = productCustom;
	}

	public ProductVo() {
		super();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public String getOldPic() {
		return oldPic;
	}

	public void setOldPic(String oldPic) {
		this.oldPic = oldPic;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public String getOldPic1() {
		return oldPic1;
	}

	public void setOldPic1(String oldPic1) {
		this.oldPic1 = oldPic1;
	}

	public String getOldPic2() {
		return oldPic2;
	}

	public void setOldPic2(String oldPic2) {
		this.oldPic2 = oldPic2;
	}

	public String getOldPic3() {
		return oldPic3;
	}

	public void setOldPic3(String oldPic3) {
		this.oldPic3 = oldPic3;
	}
	

}
