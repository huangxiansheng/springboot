package com.hs.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.springboot.dao.RoleMenuDao;
import com.hs.springboot.entity.RoleMenu;
import com.hs.springboot.service.RoleMenuService;

/**
 *@描述 
 *@日期 2018年6月1日下午2:18:31
 *@作者 vpadmin
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Override
	public List<RoleMenu> queryMenuByRole(String role) {
		return roleMenuDao.queryMenuByRole(role);
	}

}
