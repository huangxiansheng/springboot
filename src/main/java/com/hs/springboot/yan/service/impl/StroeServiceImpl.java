package com.hs.springboot.yan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.springboot.entity.HsPage;
import com.hs.springboot.yan.dao.StoreDao;
import com.hs.springboot.yan.entity.StoreData;
import com.hs.springboot.yan.entity.StoreDef;
import com.hs.springboot.yan.entity.view.StoreDataView;
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

	@Override
	public List<StoreDataView> queryStoreDate(String area,String smokeId) {
		return storeDao.queryStoreDate(area,smokeId);
	}

	@Override
	public StoreDataView queryByUuid(String uuid) {
		return storeDao.queryByUuid(uuid);
	}

	@Override
	public List<StoreDef> queryListDefByarea(String area) {
		return storeDao.queryListDefByarea(area);
	}

	@Override
	public void save(StoreData storeData) {
		storeDao.save(storeData);
	}

	@Override
	public void update(StoreData storeData) {
		storeDao.update(storeData);
	}

	@Override
	public List<StoreDataView> queryDataByUuids(String[] tmp) {
		return storeDao.queryDataByUuids(tmp);
	}

	@Override
	public void dataToHis(String[] uuids) {
		storeDao.dataToHis(uuids);
		
	}

	@Override
	public void removeUuids(String[] uuids) {
		storeDao.removeUuids(uuids);
	}

}
