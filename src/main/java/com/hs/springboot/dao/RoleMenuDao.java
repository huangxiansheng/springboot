package com.hs.springboot.dao;

import java.util.List;

import com.hs.springboot.entity.RoleMenu;

/**
 *@描述 
 *@日期 2018年6月1日上午11:14:14
 *@作者 vpadmin
 */
public interface RoleMenuDao {
	public List<RoleMenu> queryMenuByRole(String role);
}
