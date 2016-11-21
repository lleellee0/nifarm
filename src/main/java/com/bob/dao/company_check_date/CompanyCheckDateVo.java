package com.bob.dao.company_check_date;

public class CompanyCheckDateVo {
	int index;
	int company_info_index;
	int form_count;
	String check_date;
	String opinion;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getForm_count() {
		return form_count;
	}
	public void setForm_count(int form_count) {
		this.form_count = form_count;
	}
	public String getCheck_date() {
		return check_date;
	}
	public void setCheck_date(String check_date) {
		this.check_date = check_date;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public int getCompany_info_index() {
		return company_info_index;
	}
	public void setCompany_info_index(int company_info_index) {
		this.company_info_index = company_info_index;
	}
}
