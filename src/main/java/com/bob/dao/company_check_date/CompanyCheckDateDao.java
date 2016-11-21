package com.bob.dao.company_check_date;

import java.util.ArrayList;
import java.util.List;

import com.bob.dao.AbstractDao;

public class CompanyCheckDateDao {
	StringBuffer sql = new StringBuffer();
	final CompanyCheckDateVo vo = new CompanyCheckDateVo();
	final List<CompanyCheckDateVo> list = new ArrayList<CompanyCheckDateVo>();
	int result = 0;
	int count = 0;
	
	public CompanyCheckDateVo selectByIndex(final int index) {
		sql.append("SELECT * FROM ");
		sql.append("company_check_date WHERE ");
		sql.append("`index`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, index);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo.setIndex(rs.getInt(1));
					vo.setCompany_info_index(rs.getInt(2));
					vo.setForm_count(rs.getInt(3));
					vo.setCheck_date(rs.getString(4));
					vo.setOpinion(rs.getString(5));
				}
			}
		}.execute();
		return vo;
	}
	
	
	public CompanyCheckDateVo selectByCompanyInfoIndexAndFormCount(final int company_info_index, final int form_count) {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("company_check_date WHERE ");
		sql.append("`company_info_index`=? AND `form_count`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, company_info_index);
				pstmt.setInt(2, form_count);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo.setIndex(rs.getInt(1));
					vo.setCompany_info_index(rs.getInt(2));
					vo.setForm_count(rs.getInt(3));
					vo.setCheck_date(rs.getString(4));
					vo.setOpinion(rs.getString(5));
				}
			}
		}.execute();
		return vo;
	}
	
	public List<CompanyCheckDateVo> selectByCompanyInfoIndex(final int company_info_index) {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("company_check_date WHERE company_info_index=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());

				pstmt.setInt(1, company_info_index);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					CompanyCheckDateVo vo = new CompanyCheckDateVo(); 
					vo.setIndex(rs.getInt(1));
					vo.setCompany_info_index(rs.getInt(2));
					vo.setForm_count(rs.getInt(3));
					vo.setCheck_date(rs.getString(4));
					vo.setOpinion(rs.getString(5));
					list.add(vo);
				}
			}
		}.execute();
		return list;
	}
	

/*	
	public List<SubmitedFormVo> selectByScale(final int scale) {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("check_form_info WHERE scale < ?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());

				pstmt.setInt(1, scale + 1);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					SubmitedFormVo vo = new SubmitedFormVo(); 
					vo.setIndex(rs.getInt(1));
					vo.setCategory(rs.getInt(2));
					vo.setContent(rs.getString(3));
					vo.setScale(rs.getInt(4));
					vo.setCriteria(rs.getString(5));
					vo.setExample(rs.getString(6));
					list.add(vo);
				}
			}
		}.execute();
		return list;
	}
	*/
	public void insert(final CompanyCheckDateVo vo) {
		sql = new StringBuffer();
		sql.append("INSERT INTO company_check_date ");
		sql.append("(`company_info_index`, `form_count`, `check_date`, `opinion`) ");
		sql.append("VALUES (?, ?, ?, ?)");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, vo.getCompany_info_index());
				pstmt.setInt(2, vo.getForm_count());
				pstmt.setString(3, vo.getCheck_date());
				pstmt.setString(4, vo.getOpinion());
				
				pstmt.executeUpdate();
			}
		}.execute();
	}
	
	public int getCount() {
		sql = new StringBuffer();
		sql.append("SELECT count(*) FROM ");
		sql.append("company_check_date");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					count = rs.getInt(1);
				}
			}
		}.execute();

		return count;
	}
}
