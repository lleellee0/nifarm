package com.bob.dao.company_info;

import java.util.ArrayList;
import java.util.List;

import com.bob.dao.AbstractDao;

public class CompanyInfoDao {
	StringBuffer sql = new StringBuffer();
	final CompanyInfoVo vo = new CompanyInfoVo();
	final List<CompanyInfoVo> list = new ArrayList<CompanyInfoVo>();
	int result = 0;
	int count = 0;
	
	public CompanyInfoVo selectByIndex(final int index) {
		sql.append("SELECT * FROM ");
		sql.append("company_info WHERE ");
		sql.append("`index`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, index);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo.setIndex(rs.getInt(1));
					vo.setIndex(rs.getInt(2));
					vo.setCompany_name(rs.getString(3));
					vo.setLast_check_date(rs.getString(4));
					vo.setLocation(rs.getString(5));
					vo.setCheck_count(rs.getInt(6));
				}
			}
		}.execute();
		return vo;
	}
	
	public CompanyInfoVo selectByMemberIndex(final int index) {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("company_info WHERE ");
		sql.append("`member_index`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, index);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo.setIndex(rs.getInt(1));
					vo.setMember_index(rs.getInt(2));
					vo.setCompany_name(rs.getString(3));
					vo.setLast_check_date(rs.getString(4));
					vo.setLocation(rs.getString(5));
					vo.setCheck_count(rs.getInt(6));
				}
			}
		}.execute();
		return vo;
	}
	
	public CompanyInfoVo selectByCompanyId(final String companyId) {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("company_info WHERE ");
		sql.append("`company_id`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, companyId);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo.setIndex(rs.getInt(1));
					vo.setMember_index(rs.getInt(2));
					vo.setCompany_name(rs.getString(3));
					vo.setLast_check_date(rs.getString(4));
					vo.setLocation(rs.getString(5));
					vo.setCheck_count(rs.getInt(6));
					vo.setCompany_id(rs.getString(7));
				}
			}
		}.execute();
		return vo;
	}
	


	public void insert(final CompanyInfoVo vo) {
		sql = new StringBuffer();
		sql.append("INSERT INTO company_info ");
		sql.append("(`member_index`, `company_name`, `last_check_date`, `location`, `check_count`, `company_id`) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?)");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, vo.getMember_index());
				pstmt.setString(2, vo.getCompany_name());
				pstmt.setString(3, vo.getLast_check_date());
				pstmt.setString(4, vo.getLocation());
				pstmt.setInt(5, vo.getCheck_count());
				pstmt.setString(6, vo.getCompany_id());
				
				pstmt.executeUpdate();
			}
		}.execute();
	}
	
	public void update(final CompanyInfoVo vo) {
		sql = new StringBuffer();
		sql.append("UPDATE company_info ");
		sql.append("SET `member_index`=?, `company_name`=?, `last_check_date`=?, `location`=?, `check_count`=? ");
		sql.append("WHERE `index`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, vo.getMember_index());
				pstmt.setString(2, vo.getCompany_name());
				pstmt.setString(3, vo.getLast_check_date());
				pstmt.setString(4, vo.getLocation());
				pstmt.setInt(5, vo.getCheck_count());
				pstmt.setInt(6, vo.getIndex());
				pstmt.executeUpdate();
			}
		}.execute();
	}
	
	
	public List<CompanyInfoVo> selectAll() {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("company_info");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					CompanyInfoVo vo = new CompanyInfoVo(); 
					vo.setIndex(rs.getInt(1));
					vo.setMember_index(rs.getInt(2));
					vo.setCompany_name(rs.getString(3));
					vo.setLast_check_date(rs.getString(4));
					vo.setLocation(rs.getString(5));
					vo.setCheck_count(rs.getInt(6));
					vo.setCompany_id(rs.getString(7));
					
					list.add(vo);
				}
			}
		}.execute();
		return list;
	}
	
	
	public int getCount() {
		sql = new StringBuffer();
		sql.append("SELECT count(*) FROM ");
		sql.append("check_form_info");
		
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
