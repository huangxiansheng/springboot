package com.hs.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *@描述 
 *@日期 2018年5月15日上午9:22:37
 *@作者 vpadmin
 */
//@RestController = @Controller + @ResponseBody
@Controller
public class AController {

	@RequestMapping("/inpage")
	public String index() {
		return "login";
	}
	
	@RequestMapping("/th1")
	public String th1(Model model) {
		model.addAttribute("name", "Dear");
		return "a";
	}
	@RequestMapping("/th2")
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
