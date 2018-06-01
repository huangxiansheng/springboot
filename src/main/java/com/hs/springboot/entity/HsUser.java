package com.hs.springboot.entity;

import java.io.Serializable;
import java.util.List;

/**
 *@描述 
 *@日期 2018年5月31日下午1:14:06
 *@作者 vpadmin
 */
public class HsUser implements Serializable{
	
	private String uuid;
	private String userName;
	private String cnName;
	private String password;
	
	private List<Role> roles;
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
