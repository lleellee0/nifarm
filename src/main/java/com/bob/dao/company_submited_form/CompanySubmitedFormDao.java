package com.bob.dao.company_submited_form;

import java.util.ArrayList;
import java.util.List;

import com.bob.dao.AbstractDao;

public class CompanySubmitedFormDao {
	StringBuffer sql = new StringBuffer();
	final CompanySubmitedFormVo vo = new CompanySubmitedFormVo();
	final List<CompanySubmitedFormVo> list = new ArrayList<CompanySubmitedFormVo>();
	int result = 0;
	int count = 0;
	
	public CompanySubmitedFormVo selectByIndex(final int index) {
		sql.append("SELECT * FROM ");
		sql.append("company_submited_form WHERE ");
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
					vo.setCompany_check_form_info_index(rs.getInt(4));
					vo.setYpn(rs.getString(5));
					vo.setOriginal_file_name(rs.getString(6));
					vo.setFile_hash(rs.getString(7));
				}
			}
		}.execute();
		return vo;
	}
	
	public CompanySubmitedFormVo selectByCompanyInfoIndex(final int index) {
		sql.append("SELECT * FROM ");
		sql.append("company_submited_form WHERE ");
		sql.append("`company_info_index`=?");
		
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
					vo.setCompany_check_form_info_index(rs.getInt(4));
					vo.setYpn(rs.getString(5));
					vo.setOriginal_file_name(rs.getString(6));
					vo.setFile_hash(rs.getString(7));
				}
			}
		}.execute();
		return vo;
	}
	
	public List<CompanySubmitedFormVo> selectByCompanyInfoIndexAndFormCount(final int company_info_index, final int form_count) {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("company_submited_form WHERE ");
		sql.append("`company_info_index`=? AND `form_count`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, company_info_index);
				pstmt.setInt(2, form_count);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					CompanySubmitedFormVo vo = new CompanySubmitedFormVo();
					vo.setIndex(rs.getInt(1));
					vo.setCompany_info_index(rs.getInt(2));
					vo.setForm_count(rs.getInt(3));
					vo.setCompany_check_form_info_index(rs.getInt(4));
					vo.setYpn(rs.getString(5));
					vo.setOriginal_file_name(rs.getString(6));
					vo.setFile_hash(rs.getString(7));
					list.add(vo);
				}
			}
		}.execute();
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i) + " " + list.get(i).getOriginal_file_name());
		}
		
		return list;
	}
	
	public CompanySubmitedFormVo selectByCompanyInfoIndexAndFormCountAndCheckFormInfoIndex(final int company_info_index, final int form_count, final int check_form_info_index) {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("company_submited_form WHERE ");
		sql.append("`company_info_index`=? AND `form_count`=? AND `company_check_form_info_index`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, company_info_index);
				pstmt.setInt(2, form_count);
				pstmt.setInt(3, check_form_info_index);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo.setIndex(rs.getInt(1));
					vo.setCompany_info_index(rs.getInt(2));
					vo.setForm_count(rs.getInt(3));
					vo.setCompany_check_form_info_index(rs.getInt(4));
					vo.setYpn(rs.getString(5));
					vo.setOriginal_file_name(rs.getString(6));
					vo.setFile_hash(rs.getString(7));
				}
			}
		}.execute();
		return vo;
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
	public void insert(final CompanySubmitedFormVo vo) {
		sql = new StringBuffer();
		sql.append("INSERT INTO company_submited_form ");
		sql.append("(`company_info_index`, `form_count`, `company_check_form_info_index`, `ypn`, `original_file_name`, `file_hash`) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?)");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, vo.getCompany_info_index());
				pstmt.setInt(2, vo.getForm_count());
				pstmt.setInt(3, vo.getCompany_check_form_info_index());
				pstmt.setString(4, vo.getYpn());
				pstmt.setString(5, vo.getOriginal_file_name());
				pstmt.setString(6, vo.getFile_hash());
				
				pstmt.executeUpdate();
			}
		}.execute();
	}
	
	public void update(final CompanySubmitedFormVo vo) {
		sql = new StringBuffer();
		sql.append("UPDATE company_submited_form ");
		sql.append("SET company_info_index=?, form_count=?, company_check_form_info_index=?, ypn=?, original_file_name=?, file_hash=? ");
		sql.append("WHERE `index`=?");
		
		System.out.println(sql);
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, vo.getCompany_info_index());
				pstmt.setInt(2, vo.getForm_count());
				pstmt.setInt(3, vo.getCompany_check_form_info_index());
				pstmt.setString(4, vo.getYpn());
				pstmt.setString(5, vo.getOriginal_file_name());
				pstmt.setString(6, vo.getFile_hash());
				pstmt.setInt(7, vo.getIndex());
				
				pstmt.executeUpdate();
			}
		}.execute();
	}
	
	/*
	public List<BookVo> selectAll() {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("book");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BookVo vo = new BookVo(); 
					vo.setIndex(rs.getInt(1));
					vo.setRfid(rs.getInt(2));
					vo.setTitle(rs.getString(3));
					vo.setWriter(rs.getString(4));
					vo.setPublisher(rs.getString(5));
					vo.setContent(rs.getString(6));
					vo.setImg(rs.getString(7));
					vo.setBorrowed_member_index(rs.getInt(8));
					vo.setDue_date(rs.getString(9));
					list.add(vo);
				}
			}
		}.execute();
		return list;
	}
	*/
	
	public CompanySubmitedFormVo selectByFileHash(final String file_hash) {
		sql.append("SELECT * FROM ");
		sql.append("company_submited_form WHERE ");
		sql.append("`file_hash`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, file_hash);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo.setIndex(rs.getInt(1));
					vo.setCompany_info_index(rs.getInt(2));
					vo.setForm_count(rs.getInt(3));
					vo.setCompany_check_form_info_index(rs.getInt(4));
					vo.setYpn(rs.getString(5));
					vo.setOriginal_file_name(rs.getString(6));
					vo.setFile_hash(rs.getString(7));
				}
			}
		}.execute();
		return vo;
	}
	
	public int getCount() {
		sql = new StringBuffer();
		sql.append("SELECT count(*) FROM ");
		sql.append("company_check_form_info");
		
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
