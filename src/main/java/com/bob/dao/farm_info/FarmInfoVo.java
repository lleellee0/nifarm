package com.bob.dao.farm_info;

public class FarmInfoVo {
	int index;
	int member_index;
	String farm_name;
	String last_check_date;
	String location;
	int scale;
	int check_count;
	String farm_id;
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
	public String getFarm_name() {
		return farm_name;
	}
	public void setFarm_name(String farm_name) {
		this.farm_name = farm_name;
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
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public int getCheck_count() {
		return check_count;
	}
	public void setCheck_count(int check_count) {
		this.check_count = check_count;
	}
	public String getFarm_id() {
		return farm_id;
	}
	public void setFarm_id(String farm_id) {
		this.farm_id = farm_id;
	}
}
