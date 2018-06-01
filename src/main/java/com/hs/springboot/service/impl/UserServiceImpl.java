package com.hs.springboot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.springboot.dao.RoleDao;
import com.hs.springboot.dao.Userdao;
import com.hs.springboot.entity.HsUser;
import com.hs.springboot.entity.Role;
import com.hs.springboot.service.UserService;

/**
 *@描述 
 *@日期 2018年6月1日上午10:26:00
 *@作者 vpadmin
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private Userdao userDao;
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public HsUser query(String userName) {
		Map<String , String> map = new HashMap<>();
		map.put("userName", userName);
		List<HsUser> users = userDao.queryList(map);
		if(null != users && !users.isEmpty()) {
			List<Role> roles =  this.roleDao.queryRolesByUser(userName);
			HsUser user = users.get(0);
			user.setRoles(roles);
			return user;
		}
		return null;
	}

}
