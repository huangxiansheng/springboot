package com.hs.springboot.yan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.springboot.yan.service.StoreService;

/**
 *@描述 
 *@日期 2018年6月1日下午3:02:18
 *@作者 vpadmin
 */
@Controller
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	//首页查询
	@RequestMapping("/allpage")
	public String allpage() {
		return "/store/allpage";
	}
	//扫码入库页面
	@RequestMapping("/addpage")
	public String addpage() {
		return "/store/addpage";
	}
	//区域明细页面
	@RequestMapping("/detailpage")
	public String detailpage() {
		return "/store/detailpage";
	}
	//出库页面
	@RequestMapping("/removepage")
	public String removepage() {
		return "/store/removepage";
	}
	
	@RequestMapping("/queryallpage")
	@ResponseBody
	public Map<String, Object> queryallpage() {
		
		
		return new HashMap<>();
	}
	
}
