package com.hs.springboot.yan.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hs.springboot.dao.impl.BaseDaoImpl;
import com.hs.springboot.entity.HsPage;
import com.hs.springboot.yan.dao.StoreDao;
import com.hs.springboot.yan.entity.StoreDef;
import com.hs.springboot.yan.entity.StoreView;

/**
 *@描述 
 *@日期 2018年6月4日上午10:00:05
 *@作者 vpadmin
 */
@Repository
public class StoreDaoimpl extends BaseDaoImpl<StoreDef, String> implements StoreDao {

	public StoreDaoimpl() {
		super(StoreDef.class);
	}

	@Override
	public HsPage queryPageByMap(HsPage page, Map<String, String> params) {
		String sql = 
				"SELECT a.area,a.smoke_id,a.create_date,a.store_time,sum(a.smoke_number) as smoke_number FROM " + 
				"(SELECT def.area,def.area_id,da.* FROM hs_store_def def" + 
				"  LEFT JOIN hs_store_data da" + 
				"  ON def.uuid = da.uuid) a" + 
				"  WHERE a.smoke_id IS not null" + 
				"  group BY a.area,a.smoke_id,a.create_date,a.store_time";
		
		
		return this.queryForPageBySql(sql, page,StoreView.class);
	}

}
