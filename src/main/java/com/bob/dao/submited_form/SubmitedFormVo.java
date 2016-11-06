package com.bob.dao.submited_form;

public class SubmitedFormVo {
	int index;
	int farm_info_index;
	int form_count;
	int check_form_info_index;
	String ypn;
	String original_file_name;
	String file_hash;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getFarm_info_index() {
		return farm_info_index;
	}
	public void setFarm_info_index(int farm_info_index) {
		this.farm_info_index = farm_info_index;
	}
	public int getForm_count() {
		return form_count;
	}
	public void setForm_count(int form_count) {
		this.form_count = form_count;
	}
	public int getCheck_form_info_index() {
		return check_form_info_index;
	}
	public void setCheck_form_info_index(int check_form_info_index) {
		this.check_form_info_index = check_form_info_index;
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
}
