package com.bob.dao.member;

import com.bob.dao.AbstractDao;

public class MemberDao {
	StringBuffer sql = new StringBuffer();
	final MemberVo mv = new MemberVo();
	int result = 0;
	int count = 0;
	
	public MemberVo selectByIdAndPassword(final String id, final String password) {
		/*
		 * 파라미터에 final이 붙는 이유 http://devbible.tistory.com/30
		 */
		sql.append("SELECT * FROM ");
		sql.append("member WHERE ");
		sql.append("id=? AND password=password(?)");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				pstmt.setString(2, password);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					mv.setIndex(rs.getInt(1));
					mv.setId(rs.getString(2));
					mv.setPassword(rs.getString(3));
					mv.setAdmin((rs.getBoolean(4)));
					mv.setName(rs.getString(5));
				}
			}
		}.execute();
		return mv;
	}
	
	public MemberVo selectByIndex(final int index) {
		sql.append("SELECT * FROM ");
		sql.append("member WHERE ");
		sql.append("`index`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, index);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					mv.setIndex(rs.getInt(1));
					mv.setId(rs.getString(2));
					mv.setPassword(rs.getString(3));
					mv.setAdmin((rs.getBoolean(4)));
					mv.setName(rs.getString(5));
				}
			}
		}.execute();
		return mv;
	}
	
	public MemberVo selectById(final String id) {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("member WHERE");
		sql.append("`id` = ?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					mv.setIndex(rs.getInt(1));
					mv.setId(rs.getString(2));
					mv.setPassword(rs.getString(3));
					mv.setAdmin((rs.getBoolean(4)));
					mv.setName(rs.getString(5));
				}
			}
		}.execute();
		return mv;
	}
	
	public int insert(final MemberVo vo) {
		sql.append("INSERT INTO member ");
		sql.append("(`id`, `password`, `is_admin`, `name`) VALUES ");
		sql.append("(?, password(?), ?, ?)");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPassword());
				pstmt.setInt(3, 0);
				pstmt.setString(4, vo.getName());
				result = pstmt.executeUpdate();
			}
		}.execute();
		return result;
	}
	
	public int updatePasswordByIndex(final String password, final int index) {
		sql.append("UPDATE member ");
		sql.append("SET password=password(?) ");
		sql.append("WHERE `index`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, password);
				pstmt.setInt(2, index);
				result = pstmt.executeUpdate();
				
			}
		}.execute();
		return result;
	}
	
	public int getCount() {
		sql = new StringBuffer();
		sql.append("SELECT count(*) FROM ");
		sql.append("member");
		
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
