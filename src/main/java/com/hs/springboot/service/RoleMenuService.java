package com.hs.springboot.service;

import java.util.List;

import com.hs.springboot.entity.RoleMenu;

/**
 *@描述 
 *@日期 2018年6月1日下午2:17:57
 *@作者 vpadmin
 */
public interface RoleMenuService {
	public List<RoleMenu> queryMenuByRole(String role);
}
