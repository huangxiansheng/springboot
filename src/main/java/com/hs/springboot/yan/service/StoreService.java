package com.hs.springboot.yan.service;

import java.util.List;
import java.util.Map;

import com.hs.springboot.entity.HsPage;
import com.hs.springboot.yan.dao.StoreDao;
import com.hs.springboot.yan.entity.StoreDef;
import com.hs.springboot.yan.entity.view.StoreDataView;

/**
 *@描述 
 *@日期 2018年6月4日上午10:37:15
 *@作者 vpadmin
 */
public interface StoreService {
	public HsPage queryPageByMap(HsPage page,Map<String,String> params);
	
	public List<StoreDef> queryListDef();

	public List<StoreDataView> queryStoreDate(String area);
	
	
	
}
