package com.bob.mook;

import org.springframework.ui.Model;

public class SingletonSetting {
	private static SingletonSetting instance = new SingletonSetting();
	
	private String path = "/mook/";
	
	private String databaseUrl = "jdbc:mysql://localhost:3306/";
	private String databaseName = "nifarm" + "?useUnicode=true&characterEncoding=utf8";
	private String userName = "root";
	private String userPassword = "mysql123"; // Clab에 있는 노트북
	private String filePath = "C:\\Users\\jun\\Documents\\nifarm_project\\src\\main\\webapp\\WEB-INF\\files\\";
	
	private int pageSize = 15;
	
	
	
	private SingletonSetting() {}
	public static SingletonSetting getInstance() {
		return instance;
	}
	
	public String getPath() { return this.path; }
	public String getDatabaseUrl() { return this.databaseUrl; }
	public String getDatabaseName() { return this.databaseName; }
	public String getUserName() { return this.userName; }
	public String getUserPassword() { return this.userPassword; }
	public String getFilePath() { return this.filePath; }
	
	public int getPageSize() { return this.pageSize; }
	
	
	/*
	 * setAllParameter은 URL의 경로를 설정해준다.
	 * ex) <a class="text-white" href="${path}">Home</a>
     *     <a class="text-white" href="${path}list">list</a>
	 */
	public void setAllParameter(Model model) {
		model.addAttribute("path", path);
	}
}
