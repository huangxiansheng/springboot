package com.hs.springboot.service;

import java.util.List;

import com.hs.springboot.entity.Menu;

/**
 *@描述 
 *@日期 2018年6月1日下午2:17:57
 *@作者 vpadmin
 */
public interface MenuService {
	public List<Menu> queryMenuByRole(String role);
}
