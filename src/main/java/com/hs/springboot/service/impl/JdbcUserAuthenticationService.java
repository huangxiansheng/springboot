package com.hs.springboot.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hs.springboot.dao.impl.UserDaoImpl;
import com.hs.springboot.entity.HsUser;

/**
 *@描述 
 *@日期 2018年5月31日下午12:28:09
 *@作者 vpadmin
 */
@Service
public class JdbcUserAuthenticationService implements UserDetailsService{
	@Autowired
	UserDaoImpl dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO 根据用户名查询用户信息，把用户信息放入到下面那个对象
		
		ArrayList authorities = new ArrayList();
		List<HsUser> users = dao.queryList(null);
		Iterator roleList = null;//roles.iterator();

//		Role nu;
//		while (roleList.hasNext()) {
//			Object nu =  roleList.next();
//			authorities.add(new SimpleGrantedAuthority(nu.toString()));//nu.getcode
//		}
		if("000001".equals(username)) {
			authorities.add(new SimpleGrantedAuthority("admin"));//nu.getcode
		}else {
			authorities.add(new SimpleGrantedAuthority("warehouse"));//nu.getcode
		}
		
		return new User(username, users.get(0).getPassword(), authorities);
	}

}
