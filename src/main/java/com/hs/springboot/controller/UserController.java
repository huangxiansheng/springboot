package com.hs.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.springboot.entity.HsUser;
import com.hs.springboot.service.UserService;
import com.hs.springboot.util.ContextUtil;

/**
 *@描述 
 *@日期 2018年6月1日上午10:22:39
 *@作者 vpadmin
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService; 
	
	@RequestMapping("/query")
	public HsUser query() {
		User u = ContextUtil.getUserInfo();
		HsUser user = userService.query(u.getUsername());
		return user;
	}
	
}
