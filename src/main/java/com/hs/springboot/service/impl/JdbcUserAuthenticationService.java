package com.hs.springboot.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hs.springboot.entity.HsUser;
import com.hs.springboot.entity.Role;
import com.hs.springboot.service.UserService;

/**
 *@描述 
 *@日期 2018年5月31日下午12:28:09
 *@作者 vpadmin
 */
@Service
public class JdbcUserAuthenticationService implements UserDetailsService{
	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		HsUser user = userService.query(username);
		
		List<Role> roles = user.getRoles();
		if(null != roles && !roles.isEmpty()) {
			Iterator<Role> roleList = roles.iterator();
			while (roleList.hasNext()) {
				Role role =  roleList.next();
				authorities.add(new SimpleGrantedAuthority(role.getRole()));
			}
		}
		
		return new User(username, user.getPassword(), authorities);
	}

}
