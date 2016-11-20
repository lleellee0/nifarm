package com.my.sketchbook;

import java.sql.*;

public class DBDAO {
	private String jdbc_driver = "com.mysql.jdbc.Driver";
	private String jdbc_url = "jdbc:mysql://localhost:3306/chattest?characterEncoding=utf8";
	private Connection conn;
	private Statement stmt;
	
	private void connect(){
		try{
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url,"root","kgu123");
			stmt = conn.createStatement();
		}catch (Exception e){
		}
	}
	private void disconnect(){
		try{
			stmt.close();
			conn.close();
		}catch(Exception e){
		}
	}

	public MemberBean getMember(String id){
		String sql = "select m_id,m_pw,m_name from member where m_id='"+id+"'";
		MemberBean member = new MemberBean();
		try{
			connect();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			member.setM_id(rs.getString("m_id"));
			member.setM_pw(rs.getString("m_pw"));
			member.setM_name(rs.getString("m_name"));
			rs.close();
			disconnect();
		}catch(Exception e){
		}
		return member;
	}
}
