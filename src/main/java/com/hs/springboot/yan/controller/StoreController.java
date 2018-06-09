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
import com.hs.springboot.yan.entity.StoreDef;
import com.hs.springboot.yan.entity.view.StoreDataView;
import com.hs.springboot.yan.entity.view.StoreView;
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
	//区域明细-修改页面
	@RequestMapping("/modify")
	public String modify() {
		return "/store/modify";
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
		
		List<StoreView> list = rePage.getResultData();
		
		map.put("rows", rePage.getResultData());//返回的数据
		map.put("total", rePage.getTotal());//【返回】共多少数据
		return map;
	}
	
	@RequestMapping("/queryarea")
	@ResponseBody
	public List<StoreDef> queryarea(){
		return storeService.queryListDef();
	}
	
	@RequestMapping("/queryData")
	@ResponseBody
	public List<StoreDataView> queryStoreDate(HttpServletRequest request){
		String area = this.getRequestParams(request).get("area");
		return storeService.queryStoreDate(area);
	}
	@RequestMapping("/queryByUuid")
	@ResponseBody
	public StoreDataView queryByUuid(HttpServletRequest request){
		String uuid = this.getRequestParams(request).get("uuid");
		return storeService.queryByUuid(uuid);
	}
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/querysmokeId")
	@ResponseBody
	public List<Map<String,String>> querysmokeId(){
		List<Map<String,String>> list  = new ArrayList<Map<String,String>>();
		
		Map map1 = new HashMap<>();
		map1.put("smokeId", "A001");
		map1.put("name", "这个烟");
		list.add(map1);
		
		Map map2 = new HashMap<>();
		map2.put("smokeId", "A002");
		map2.put("name", "那个烟");
		list.add(map2);
		
		return list;
	}
	
	
}
