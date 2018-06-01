package com.hs.springboot.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hs.springboot.dao.RoleMenuDao;
import com.hs.springboot.entity.RoleMenu;

/**
 *@描述 
 *@日期 2018年6月1日上午11:15:09
 *@作者 vpadmin
 */
@Repository
public class RoleMenuDaoImpl implements RoleMenuDao {

	
	private static List<RoleMenu> adminMenus = new ArrayList<RoleMenu>();
	private static List<RoleMenu> warehouseMenus = new ArrayList<RoleMenu>();
	private static List<RoleMenu> leaderMenus = new ArrayList<RoleMenu>();
	
	static {
		RoleMenu rm= new RoleMenu();
		List<String> adminUrls = new ArrayList<>();
		adminUrls.add("/a");
		rm.setUrls(adminUrls);
		adminMenus.add(rm);
	}
	
	@Override
	public List<RoleMenu> queryMenuByRole(String role) {
		//TODO 此处是写死的
		if("admin".equals(role)) {
			return adminMenus;
		}
		if("warehouse".equals(role)) {
			return warehouseMenus;
		}
		if("leader".equals(role)) {
			return leaderMenus;
		}
		return null;
	}

}
