package com.mall.ssm.po.result;

public class SendTempleteMsg {
	private String touser;//openid
	private String template_id;//模板id
	private String page;			//页面
	private String form_id;			//表格id
	private Keywords data;			//
	
	public SendTempleteMsg(TempleteArg arg) {
		this.form_id = arg.getForm_id();
		this.page = arg.getPage();
		
		Keyword keyword1 = new Keyword(arg.getOrderNumber());
		Keyword keyword2 = new Keyword(arg.getAmount());
		Keyword keyword3 = new Keyword(arg.getDateAdd());
		this.data = new Keywords(keyword1,keyword2,keyword3);
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}

	public Keywords getData() {
		return data;
	}

	public void setData(Keywords data) {
		this.data = data;
	}

	public SendTempleteMsg(String touser, String template_id, String page,
			String form_id, Keywords data) {
		super();
		this.touser = touser;
		this.template_id = template_id;
		this.page = page;
		this.form_id = form_id;
		this.data = data;
	}

	public SendTempleteMsg() {
		super();
	}
	
}
