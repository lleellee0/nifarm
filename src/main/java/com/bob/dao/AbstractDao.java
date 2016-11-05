package com.bob.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bob.mook.SingletonSetting;

public abstract class AbstractDao {
	protected Connection con;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	public final void execute() {
		try {
			init();
			query();
			close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init() throws Exception {
		SingletonSetting ssi = SingletonSetting.getInstance();
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				ssi.getDatabaseUrl() + ssi.getDatabaseName(),	// URL + DB Name 
				ssi.getUserName(), ssi.getUserPassword()		// UserName, Password
		);
	}
	
	public abstract void query() throws Exception;				// 각 Dao에서 구현.
	
	private void close() {
		if(rs != null) try {rs.close();} catch (Exception e) {e.printStackTrace();};
		if(pstmt != null) try {pstmt.close();} catch (Exception e) {e.printStackTrace();};
		if(con != null) try {con.close();} catch (Exception e) {e.printStackTrace();};
	}
}
