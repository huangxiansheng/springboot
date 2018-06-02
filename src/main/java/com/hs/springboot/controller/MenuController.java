package com.hs.springboot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.springboot.entity.Menu;
import com.hs.springboot.service.MenuService;
import com.hs.springboot.util.ContextUtil;

@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/tree")
	public List<Menu> menuTree() {
		//TODO 根据用户查询菜单
		User u = ContextUtil.getUserInfo();
		
		Collection<GrantedAuthority> roles = u.getAuthorities();
		List<Menu> menus = new ArrayList<Menu>();
		if( null !=roles && !roles.isEmpty()) {
			for (GrantedAuthority grantedAuthority : roles) {
				String role = grantedAuthority.getAuthority();
				menus.addAll(this.menuService.queryMenuByRole(role));
			}
		}
		
		List<Menu> list = new ArrayList<>();
		
		for (Menu menu : menus) {
			if(list.contains(menu)) {
				continue;
			}
			list.add(menu);
		}
		return list;
	}
}
