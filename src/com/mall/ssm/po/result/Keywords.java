package com.mall.ssm.po.result;

public class Keywords {
	private Keyword keyword1;
	private Keyword keyword2;
	private Keyword keyword3;
	public Keyword getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(Keyword keyword1) {
		this.keyword1 = keyword1;
	}
	public Keyword getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(Keyword keyword2) {
		this.keyword2 = keyword2;
	}
	public Keyword getKeyword3() {
		return keyword3;
	}
	public void setKeyword3(Keyword keyword3) {
		this.keyword3 = keyword3;
	}
	public Keywords(Keyword keyword1, Keyword keyword2, Keyword keyword3) {
		super();
		this.keyword1 = keyword1;
		this.keyword2 = keyword2;
		this.keyword3 = keyword3;
	}
	public Keywords() {
		super();
	}
	
}
