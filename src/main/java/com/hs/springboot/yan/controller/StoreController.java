package com.hs.springboot.yan.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.springboot.controller.BaseController;
import com.hs.springboot.entity.HsPage;
import com.hs.springboot.util.ContextUtil;
import com.hs.springboot.yan.entity.StoreData;
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
	public String modify(HttpServletRequest request,Model model) {
		String uuid = this.getRequestParams(request).get("uuid");
		StoreDataView sdv = storeService.queryByUuid(uuid);
		model.addAttribute("sdv", sdv);
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
	@RequestMapping("/queryareaId")
	@ResponseBody
	public List<StoreDef> queryareaId(HttpServletRequest request){
		String area = this.getRequestParams(request).get("area");
		return storeService.queryListDefByarea(area);
	}
	
	@RequestMapping("/queryData")
	@ResponseBody
	public List<StoreDataView> queryStoreDate(HttpServletRequest request){
		String area = this.getRequestParams(request).get("area");
		String smokeId = this.getRequestParams(request).get("smokeId");
		return storeService.queryStoreDate(area,smokeId);
	}
	@RequestMapping("/queryByUuid")
	@ResponseBody
	public StoreDataView queryByUuid(HttpServletRequest request){
		String uuid = this.getRequestParams(request).get("uuid");
		return storeService.queryByUuid(uuid);
	}

	@RequestMapping("/checkSaoma")
	@ResponseBody
	public boolean checkSaoma(HttpServletRequest request) {
		String saoma = this.getRequestParams(request).get("saoma");//根据扫码获取，烟品牌和生产日期
		
		String smokeId = "";
		Date createDate = null;
		if(saoma.length()==31) {
			smokeId = saoma.substring(2, 8);
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			try {
				createDate = sdf.parse(saoma.substring(15, 21));
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(HttpServletRequest request){
		Map<String,Object> reMap = new HashMap<>();
		try {
			
			StoreData storeData = new StoreData(); 
			
			String saoma = this.getRequestParams(request).get("saoma");//根据扫码获取，烟品牌和生产日期
			//(91)6位烟+7位？+6位日期+10位？？
			String smokeId = "";
			Date createDate = null;
			if(saoma.length()==31) {
				smokeId = saoma.substring(2, 8);
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
				createDate = sdf.parse(saoma.substring(15, 21));
			}
			
			String tray = this.getRequestParams(request).get("tray");
			String smokeNumber = this.getRequestParams(request).get("smokeNumber");
			String area = this.getRequestParams(request).get("area");
			String areaId = this.getRequestParams(request).get("areaId");
			
			String uuid = area +"-"+areaId;
			
			storeData.setCreateDate(createDate);
			storeData.setSmokeId(smokeId);
			storeData.setUuid(uuid);
			storeData.setTray(tray);
			storeData.setSmokeNumber(Integer.valueOf(smokeNumber));
			storeData.setStoreTime(new Date());
			storeData.setStoreUser(ContextUtil.getUserInfo().getUsername());
			
			
			storeService.save(storeData);
			
			reMap.put("flag", true);
		} catch (Exception e) {
			e.printStackTrace();
			reMap.put("flag", false);
			reMap.put("msg", e.getMessage());
		}
		return reMap;
	}
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(HttpServletRequest request) throws ParseException {
		Map<String,Object> reMap = new HashMap<>();
		
		String uuid = this.getRequestParams(request).get("uuid");
		String tray = this.getRequestParams(request).get("tray");
		String smokeNumber = this.getRequestParams(request).get("smokeNumber");
		String smokeId = this.getRequestParams(request).get("smokeId");
		String createDate = this.getRequestParams(request).get("createDate");
		
		StoreData storeData = new StoreData();
		storeData.setUuid(uuid);
		storeData.setTray(tray);
		storeData.setSmokeNumber(Integer.valueOf(smokeNumber));
		storeData.setSmokeId(smokeId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		storeData.setCreateDate(sdf.parse(createDate));
		
		storeService.update(storeData);
		reMap.put("flag", true);
		return reMap;
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
	
	@RequestMapping("/exp")
	public List<Map<String,String>> exp(HttpServletResponse response){
		 //文件类型
        response.setHeader("content-Type", "application/vnd.ms-excel");
        try {
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("出库.xlsx", "utf-8"));
		} catch (UnsupportedEncodingException e) {
		}
        
        
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
