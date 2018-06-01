package com.hs.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.springboot.dao.impl.UserDaoImpl;
import com.hs.springboot.entity.HsUser;
import com.hs.springboot.util.ContextUtil;

/**
 *@描述 
 *@日期 2018年5月15日上午9:22:37
 *@作者 vpadmin
 */
//@RestController = @Controller + @ResponseBody
@Controller
public class AController {

	@Autowired
	private UserDaoImpl userDao;
	
	@RequestMapping("/in_page")
	public String in_page() {
		return "in_page";
	}
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/a")
	public String th1(Model model) {
		model.addAttribute("name", "Dear");
		
		User u = ContextUtil.getUserInfo();
		
		return "a";
	}
	@RequestMapping("/b")
	public String th2() {
		return "b";
	}
	@RequestMapping("/c")
	public String th3() {
		return "c";
	}
}
