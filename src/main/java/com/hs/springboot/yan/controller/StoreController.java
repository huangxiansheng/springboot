package com.hs.springboot.yan.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.springboot.controller.BaseController;
import com.hs.springboot.entity.HsPage;
import com.hs.springboot.yan.entity.StoreView;
import com.hs.springboot.yan.service.StoreService;

/**
 *@描述 
 *@日期 2018年6月1日下午3:02:18
 *@作者 vpadmin
 */
@Controller
@RequestMapping("/store")
public class StoreController extends BaseController{
	
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
	public Map<String, Object> queryallpage(HttpServletRequest request) {
		//【分页】  page 是第几页，rows表示每页 多少行
		//【排序】sort: area order: asc
		//按区域查询，要根据  区域，烟id，生产日期，入库日期，汇总
		Map<String, Object> map = new HashMap<>(); 
		HsPage page = this.getPage(request);
		
		Map params = this.getRequestParams(request);
		
		HsPage rePage = storeService.queryPageByMap(page, params);
		
		
		map.put("rows", rePage.getResultData());//返回的数据
		map.put("total", rePage.getTotal());//【返回】共多少数据
		
		return map;
	}
	
}
