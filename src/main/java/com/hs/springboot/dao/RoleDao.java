package com.hs.springboot.dao;

import java.util.List;

import com.hs.springboot.entity.Role;

/**
 *@描述 
 *@日期 2018年6月1日上午10:42:08
 *@作者 vpadmin
 */
public interface RoleDao {
	
	public List<Role> queryRolesByUser(String userName);
}
