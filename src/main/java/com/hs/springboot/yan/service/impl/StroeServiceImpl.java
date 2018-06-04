package com.hs.springboot.yan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.springboot.entity.HsPage;
import com.hs.springboot.yan.dao.StoreDao;
import com.hs.springboot.yan.entity.StoreDef;
import com.hs.springboot.yan.service.StoreService;

/**
 *@描述 
 *@日期 2018年6月4日上午10:38:16
 *@作者 vpadmin
 */
@Service
public class StroeServiceImpl implements StoreService {

	@Autowired
	public StoreDao storeDao;
	
	@Override
	public HsPage queryPageByMap(HsPage page,Map<String, String> params) {
		return storeDao.queryPageByMap(page, params);
	}

	@Override
	public List<StoreDef> queryListDef() {
		return storeDao.queryListDef();
	}

}
