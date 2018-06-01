package com.hs.springboot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.springboot.util.ContextUtil;

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@RequestMapping("/tree")
	public List menuTree() {
		//TODO 根据用户查询菜单
		User u = ContextUtil.getUserInfo();
		
		Collection<GrantedAuthority> roles = u.getAuthorities();
		
		Map map = new HashMap<>();
		map.put("id", "1");
		map.put("text", "asdasd");
		map.put("url", "/b");
		
		List list = new ArrayList<>();
		list.add(map);
		return list;
	}
}
