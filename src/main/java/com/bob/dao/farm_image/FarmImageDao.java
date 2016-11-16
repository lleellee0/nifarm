package com.bob.dao.farm_image;

import java.util.ArrayList;
import java.util.List;

import com.bob.dao.AbstractDao;

public class FarmImageDao {
	StringBuffer sql = new StringBuffer();
	final FarmImageVo vo = new FarmImageVo();
	final List<FarmImageVo> list = new ArrayList<FarmImageVo>();
	int result = 0;
	int count = 0;
	
	public FarmImageVo selectByFarmInfoIndexAndFormCount(final int farm_info_index, final int form_count) {
		System.out.println("----------------------" + farm_info_index + " " + form_count);
		
		sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("farm_image WHERE ");
		sql.append("`farm_info_index`=? AND `form_count`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, farm_info_index);
				pstmt.setInt(2, form_count);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo.setIndex(rs.getInt(1));
					vo.setFarm_info_index(rs.getInt(2));
					vo.setForm_count(rs.getInt(3));
					vo.setOriginal_file_name(rs.getString(4));
					vo.setFile_hash(rs.getString(5));
				}
			}
		}.execute();
		
		System.out.println("^^^^^^^^^" + vo.getFile_hash());
		
		return vo;
	}
	

	public void insert(final FarmImageVo vo) {
		sql = new StringBuffer();
		sql.append("INSERT INTO farm_image ");
		sql.append("(`farm_info_index`, `form_count`, `original_file_name`, `file_hash`) ");
		sql.append("VALUES (?, ?, ?, ?)");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, vo.getFarm_info_index());
				pstmt.setInt(2, vo.getForm_count());
				pstmt.setString(3, vo.getOriginal_file_name());
				pstmt.setString(4, vo.getFile_hash());
				
				pstmt.executeUpdate();
			}
		}.execute();
	}
	
	public FarmImageVo selectByFileHash(final String file_hash) {
		sql.append("SELECT * FROM ");
		sql.append("farm_image WHERE ");
		sql.append("`file_hash`=?");
		
		new AbstractDao() {
			@Override
			public void query() throws Exception {
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, file_hash);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					vo.setIndex(rs.getInt(1));
					vo.setFarm_info_index(rs.getInt(2));
					vo.setForm_count(rs.getInt(3));
					vo.setOriginal_file_name(rs.getString(4));
					vo.setFile_hash(rs.getString(5));
				}
			}
		}.execute();
		return vo;
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
