package com.bob.dao.farm_info;

import java.util.ArrayList;
import java.util.List;

import com.bob.dao.AbstractDao;

public class FarmInfoDao {
	StringBuffer sql = new StringBuffer();
	final FarmInfoVo vo = new FarmInfoVo();
	final List<FarmInfoVo> list = new ArrayList<FarmInfoVo>();
	int result = 0;
	int count = 0;
	
	public FarmInfoVo selectByIndex(final int index) {
		sql.append("SELECT * FROM ");
		sql.append("farm_info WHERE ");
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
					vo.setFarm_name(rs.getString(3));
					vo.setLast_check_date(rs.getString(4));
					vo.setLocation(rs.getString(5));
					vo.setScale(rs.getInt(6));
					vo.setCheck_count(rs.getInt(7));
				}
			}
		}.execute();
		return vo;
	}
	
	public FarmInfoVo selectByMemberIndex(final int index) {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("farm_info WHERE ");
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
					vo.setFarm_name(rs.getString(3));
					vo.setLast_check_date(rs.getString(4));
					vo.setLocation(rs.getString(5));
					vo.setScale(rs.getInt(6));
					vo.setCheck_count(rs.getInt(7));
				}
			}
		}.execute();
		return vo;
	}
	
	public FarmInfoVo selectByFarmId(final String farmId) {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("farm_info WHERE ");
		sql.append("`farm_id`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, farmId);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo.setIndex(rs.getInt(1));
					vo.setMember_index(rs.getInt(2));
					vo.setFarm_name(rs.getString(3));
					vo.setLast_check_date(rs.getString(4));
					vo.setLocation(rs.getString(5));
					vo.setScale(rs.getInt(6));
					vo.setCheck_count(rs.getInt(7));
					vo.setFarm_id(rs.getString(8));
				}
			}
		}.execute();
		return vo;
	}
	

/*	
	public List<FarmInfoVo> selectByScale(final int scale) {
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
					FarmInfoVo vo = new FarmInfoVo(); 
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
	public void insert(final FarmInfoVo vo) {
		sql = new StringBuffer();
		sql.append("INSERT INTO farm_info ");
		sql.append("(`member_index`, `farm_name`, `last_check_date`, `location`, `scale`, `check_count`, `farm_id`) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?)");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, vo.getMember_index());
				pstmt.setString(2, vo.getFarm_name());
				pstmt.setString(3, vo.getLast_check_date());
				pstmt.setString(4, vo.getLocation());
				pstmt.setInt(5, vo.getScale());
				pstmt.setInt(6, vo.getCheck_count());
				pstmt.setString(7, vo.getFarm_id());
				
				pstmt.executeUpdate();
			}
		}.execute();
	}
	
	public void update(final FarmInfoVo vo) {
		sql = new StringBuffer();
		sql.append("UPDATE farm_info ");
		sql.append("SET `member_index`=?, `farm_name`=?, `last_check_date`=?, `location`=?, `scale`=?, `check_count`=? ");
		sql.append("WHERE `index`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, vo.getMember_index());
				pstmt.setString(2, vo.getFarm_name());
				pstmt.setString(3, vo.getLast_check_date());
				pstmt.setString(4, vo.getLocation());
				pstmt.setInt(5, vo.getScale());
				pstmt.setInt(6, vo.getCheck_count());
				pstmt.setInt(7, vo.getIndex());
				pstmt.executeUpdate();
			}
		}.execute();
	}
	
	
	public List<FarmInfoVo> selectAll() {
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("farm_info");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					FarmInfoVo vo = new FarmInfoVo(); 
					vo.setIndex(rs.getInt(1));
					vo.setMember_index(rs.getInt(2));
					vo.setFarm_name(rs.getString(3));
					vo.setLast_check_date(rs.getString(4));
					vo.setLocation(rs.getString(5));
					vo.setScale(rs.getInt(6));
					vo.setCheck_count(rs.getInt(7));
					vo.setFarm_id(rs.getString(8));
					
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
