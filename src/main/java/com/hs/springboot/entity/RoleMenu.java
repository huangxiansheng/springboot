package com.hs.springboot.entity;
/**
 *@描述 
 *@日期 2018年6月1日上午11:12:39
 *@作者 vpadmin
 */

import java.io.Serializable;
import java.util.List;

public class RoleMenu implements Serializable{

	private String role;
	private List<String> urls ;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
}
