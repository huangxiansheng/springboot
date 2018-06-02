package com.hs.springboot.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.hs.springboot.dao.MenuDao;
import com.hs.springboot.entity.Menu;

/**
 *@描述 
 *@日期 2018年6月1日上午11:15:09
 *@作者 vpadmin
 */
@Repository
public class MenuDaoImpl implements MenuDao {

	
	private static List<Menu> adminMenus = new ArrayList<Menu>();
	private static List<Menu> warehouseMenus = new ArrayList<Menu>();
	private static List<Menu> leaderMenus = new ArrayList<Menu>();
	//首页查询
	//@RequestMapping("/allpage")
	//扫码入库页面
	//@RequestMapping("/addpage")
	//区域明细页面
	//@RequestMapping("/detailpage")
	//出库页面
	//@RequestMapping("/removepage")
	static {
		Menu m1= new Menu();
		m1.setId("1");
		m1.setUrl("/store/allpage");
		m1.setText("首页");
		
		Menu m2= new Menu();
		m2.setId("2");
		m2.setUrl("/store/addpage");
		m2.setText("扫码入库");
		
		Menu m3= new Menu();
		m3.setId("3");
		m3.setUrl("/store/detailpage");
		m3.setText("区域明细");
		
		Menu m4= new Menu();
		m4.setId("4");
		m4.setUrl("/store/removepage");
		m4.setText("出库页面");
		
		adminMenus.add(m1);
		adminMenus.add(m2);
		adminMenus.add(m3);
		adminMenus.add(m4);
		
		leaderMenus.add(m1);
		leaderMenus.add(m2);
		leaderMenus.add(m3);
		leaderMenus.add(m4);
	}
	
	@Override
	public List<Menu> queryMenuByRole(String role) {
		//TODO 此处是写死的
		if("ROLE_ADMIN".equals(role)) {
			return adminMenus;
		}
		if("ROLE_WAREHOUSE".equals(role)) {
			return warehouseMenus;
		}
		if("ROLE_LEADER".equals(role)) {
			return leaderMenus;
		}
		return null;
	}

}
