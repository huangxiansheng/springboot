package com.hs.springboot.yan.controller;

import java.io.IOException;
import java.io.OutputStream;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void exp(HttpServletRequest request,HttpServletResponse response){
		 //文件类型
        response.setHeader("content-Type", "application/vnd.ms-excel");
        try {
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode("出库"+DateFormatUtils.format(new Date(), "yyyy-MM-dd--HHmmss")+".xlsx", "utf-8"));
		} catch (UnsupportedEncodingException e) {
		}
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("第一sheet页");
        
        XSSFCellStyle titleStyle = createTitleStyle(wb, 24, HSSFFont.BOLDWEIGHT_NORMAL, HSSFCellStyle.ALIGN_CENTER);
        
        String[] titleNames = {"区域","货号","品牌","数量"};
        // 创建第一行：表头
        XSSFRow titleRow = sheet.createRow(0);
        createTitleRow(titleRow,null, titleNames);

        //查询数据,只按选择的UUID来查，多选
        List<StoreDataView> lists = null;
        String selectStores = this.getRequestParams(request).get("selectStore");
        String[] uuids = {selectStores};
        if(StringUtils.isNotEmpty(selectStores)) {
        	if(selectStores.indexOf(",")>0) {
        		uuids = selectStores.split(",");
        	}
        	lists = storeService.queryDataByUuids(uuids);
        }
        
		//放入数据
        if(null != lists && !lists.isEmpty()) {
        	int leng = lists.size();
        	for(int i = 0 ; i< leng ; i++) {
        		XSSFRow dataRow = sheet.createRow(i+1);
        		createDateRow(dataRow,null,lists.get(i));
        	}
        }
		
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			wb.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			//TODO 删除出库的数据并放入到历史记录中
			storeService.dataToHis(uuids);
			storeService.removeUuids(uuids);
		}
	}
	
	private void createDateRow(XSSFRow dataRow, XSSFCellStyle titleStyle, StoreDataView storeDataView) {
		XSSFCell cellTitle0 = dataRow.createCell(0);//区域
		cellTitle0.setCellStyle(titleStyle);
		cellTitle0.setCellValue(storeDataView.getArea());
		
		XSSFCell cellTitle1 = dataRow.createCell(1);//编号
		cellTitle1.setCellStyle(titleStyle);
		cellTitle1.setCellValue(storeDataView.getAreaId());
		
		XSSFCell cellTitle2 = dataRow.createCell(2);//品牌
		cellTitle2.setCellStyle(titleStyle);
		cellTitle2.setCellValue(storeDataView.getSmokeId());
		
		XSSFCell cellTitle3 = dataRow.createCell(3);//数量
		cellTitle3.setCellStyle(titleStyle);
		cellTitle3.setCellValue(storeDataView.getSmokeNumber());
		
	}
	private void createTitleRow(XSSFRow titleRow, XSSFCellStyle titleStyle,String[] titleNames) {
		int i = 0;
		for (String name : titleNames) {
			XSSFCell cellTitle = titleRow.createCell(i++);
			cellTitle.setCellStyle(titleStyle);
			cellTitle.setCellValue(name);
		}
	}
	// 表头使用无边框
	private XSSFCellStyle createTitleStyle(XSSFWorkbook wb, int fontSize, int boldweight, short align) {
		XSSFCellStyle styleTitle = wb.createCellStyle();// 生成另外一个样式
		XSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) fontSize);// 设置字体大小
		font.setBoldweight((short) boldweight);
		styleTitle.setFont(font);
		styleTitle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
		styleTitle.setAlignment(align);
		return styleTitle;
	}
}
