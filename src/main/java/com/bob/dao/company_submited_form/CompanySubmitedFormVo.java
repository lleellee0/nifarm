package com.bob.dao.company_submited_form;

public class CompanySubmitedFormVo {
	int index;
	int company_info_index;
	int form_count;
	int company_check_form_info_index;
	String ypn;
	String original_file_name;
	String file_hash;
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

	public String getYpn() {
		return ypn;
	}
	public void setYpn(String ypn) {
		this.ypn = ypn;
	}
	public String getOriginal_file_name() {
		return original_file_name;
	}
	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}
	public String getFile_hash() {
		return file_hash;
	}
	public void setFile_hash(String file_hash) {
		this.file_hash = file_hash;
	}
	public int getCompany_info_index() {
		return company_info_index;
	}
	public void setCompany_info_index(int company_info_index) {
		this.company_info_index = company_info_index;
	}
	public int getCompany_check_form_info_index() {
		return company_check_form_info_index;
	}
	public void setCompany_check_form_info_index(int company_check_form_info_index) {
		this.company_check_form_info_index = company_check_form_info_index;
	}
}
