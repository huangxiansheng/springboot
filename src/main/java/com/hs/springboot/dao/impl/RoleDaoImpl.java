package com.hs.springboot.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hs.springboot.dao.RoleDao;
import com.hs.springboot.entity.Role;

/**
 *@描述 
 *@日期 2018年6月1日上午10:42:59
 *@作者 vpadmin
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role, String> implements RoleDao{
	public RoleDaoImpl() {
		super(Role.class);
	}
	@Override
	public List<Role> queryRolesByUser(String userName) {
		String sql = "select * from hs_user_role where user_name = '" + userName + "'";
		List<Role> roles = this.queryListBysql(sql);
		return roles;
	}

}
