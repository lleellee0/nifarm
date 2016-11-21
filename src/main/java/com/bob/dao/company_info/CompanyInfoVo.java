package com.bob.dao.company_info;

public class CompanyInfoVo {
	int index;
	int member_index;
	String company_name;
	String last_check_date;
	String location;
	int check_count;
	String company_id;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getMember_index() {
		return member_index;
	}
	public void setMember_index(int member_index) {
		this.member_index = member_index;
	}

	public String getLast_check_date() {
		return last_check_date;
	}
	public void setLast_check_date(String last_check_date) {
		this.last_check_date = last_check_date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public int getCheck_count() {
		return check_count;
	}
	public void setCheck_count(int check_count) {
		this.check_count = check_count;
	}

	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
}
