package com.bob.dao.check_form_info;

import java.util.ArrayList;
import java.util.List;

import com.bob.dao.AbstractDao;

public class CheckFormInfoDao {
	StringBuffer sql = new StringBuffer();
	final CheckFormInfoVo cv = new CheckFormInfoVo();
	final List<CheckFormInfoVo> list = new ArrayList<CheckFormInfoVo>();
	int result = 0;
	int count = 0;
	
	public CheckFormInfoVo selectByIndex(final int index) {
		sql.append("SELECT * FROM ");
		sql.append("check_form_info WHERE ");
		sql.append("`index`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, index);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					cv.setIndex(rs.getInt(1));
					cv.setCategory(rs.getInt(2));
					cv.setContent(rs.getString(3));
					cv.setScale((rs.getInt(4)));
					cv.setCriteria(rs.getString(5));
					cv.setExample(rs.getString(6));
				}
			}
		}.execute();
		return cv;
	}
	
	public List<CheckFormInfoVo> selectByScale(final int scale) {
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
					CheckFormInfoVo vo = new CheckFormInfoVo(); 
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
