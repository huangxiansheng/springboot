package com.hs.springboot.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hs.springboot.dao.Userdao;
import com.hs.springboot.entity.HsUser;

/**
 *@描述 
 *@日期 2018年5月31日下午12:56:22
 *@作者 vpadmin
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<HsUser, String> implements Userdao{

	public UserDaoImpl() {
		super(HsUser.class);
	}

	@Override
	public List<HsUser> queryList(Map<String, String> map) {
		String userName = map.get("userName");
		String sql = "select * from hs_user where user_name = '" + userName + "'";
		return super.queryListBysql(sql);
	}

	
}
