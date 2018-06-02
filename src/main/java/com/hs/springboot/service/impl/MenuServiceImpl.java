package com.hs.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.springboot.dao.MenuDao;
import com.hs.springboot.entity.Menu;
import com.hs.springboot.service.MenuService;

/**
 *@描述 
 *@日期 2018年6月1日下午2:18:31
 *@作者 vpadmin
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Override
//	@RolesAllowed(value = {"admin","warehouse"})
//	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Menu> queryMenuByRole(String role) {
		return menuDao.queryMenuByRole(role);
	}

}
