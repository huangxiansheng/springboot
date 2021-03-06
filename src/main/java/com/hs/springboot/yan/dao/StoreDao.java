package com.hs.springboot.yan.dao;

import java.util.List;
import java.util.Map;

import com.hs.springboot.entity.HsPage;
import com.hs.springboot.yan.entity.StoreData;
import com.hs.springboot.yan.entity.StoreDef;
import com.hs.springboot.yan.entity.view.StoreDataView;

/**
 *@描述 
 *@日期 2018年6月4日上午9:59:37
 *@作者 vpadmin
 */
public interface StoreDao {
	public HsPage queryPageByMap(HsPage page,Map<String, String> params) ;
	public List<StoreDef> queryListDef();
	public List<StoreDataView> queryStoreDate(String area, String smokeId);
	public StoreDataView queryByUuid(String uuid);
	public List<StoreDef> queryListDefByarea(String area);
	public void save(StoreData storeData);
	public void update(StoreData storeData);
	public List<StoreDataView> queryDataByUuids(String[] tmp);
	public void dataToHis(String[] uuids);
	public void removeUuids(String[] uuids);
}
