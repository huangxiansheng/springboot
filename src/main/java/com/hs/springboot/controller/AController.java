package com.hs.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.springboot.dao.impl.UserDaoImpl;
import com.hs.springboot.entity.HsUser;

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
		
		List<HsUser> user = userDao.queryList(null);
		System.out.println("a--------------");
		return "inde";
	}
	
	@RequestMapping("/a")
	public String th1(Model model) {
		model.addAttribute("name", "Dear");
		return "a";
	}
	@RequestMapping("/b")
	public String th2() {
		
		return "b";
	}
	
	@RequestMapping("/9xx")
	public String xx() {
		if(true) {
			throw new RuntimeException("daslflskdfj");
		}
		return "b";
	}
}
