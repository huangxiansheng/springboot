package com.hs.springboot.yan.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hs.springboot.dao.impl.BaseDaoImpl;
import com.hs.springboot.entity.HsPage;
import com.hs.springboot.yan.dao.StoreDao;
import com.hs.springboot.yan.entity.StoreDef;
import com.hs.springboot.yan.entity.view.StoreDataView;
import com.hs.springboot.yan.entity.view.StoreView;

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
		String area = params.get("area");
		String smokeId = params.get("smokeId");
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		
		
		StringBuilder sql = new StringBuilder("SELECT a.area,a.smoke_id,a.create_date,a.store_time,sum(a.smoke_number) as smoke_number FROM ")
				 .append("(SELECT def.area,def.area_id,da.* FROM hs_store_def def")
				 .append(" LEFT JOIN hs_store_data da")
				 .append(" ON def.uuid = da.uuid ")
				 .append(" WHERE da.smoke_id IS not null");
		
		//添加查询条件
		if(StringUtils.isNotEmpty(area)) {
			sql = sql.append(" and def.area = '"+area+"'");
		}
		if(StringUtils.isNotEmpty(smokeId)) {
			sql = sql.append(" and da.smoke_Id = '"+smokeId+"'");
		}
		if(StringUtils.isNotEmpty(date1)) {
			//str_to_date(‘2017-10-16 15:30:28’, ‘%Y-%m-%d %h:%i:%s’); 
			sql = sql.append(" and da.create_date >= str_to_date('"+date1+"','%Y-%m-%d')");
		}
		if(StringUtils.isNotEmpty(date2)) {
			sql = sql.append(" and da.create_date <= str_to_date('"+date2+"','%Y-%m-%d')");
		}
		
		sql = sql.append(") a")
				.append(" group BY a.area,a.smoke_id,a.create_date,a.store_time");
		
		
		return this.queryForPageBySql(sql.toString(), page,StoreView.class);
	}

	@Override
	public List<StoreDef> queryListDef() {
		String sql = "select area from hs_store_def group by area";
		return super.queryListBysql(sql, StoreDef.class);
	}

	@Override
	public List<StoreDataView> queryStoreDate(String area) {
		StringBuilder sql = new StringBuilder("");
		 sql = sql.append("SELECT def.area,def.area_id,da.* FROM hs_store_def def")
				 .append(" LEFT JOIN hs_store_data da")
				 .append(" ON def.uuid = da.uuid ");
		if(StringUtils.isNotEmpty(area)) {
			sql = sql.append(" where def.area = '"+area+"'");
		}
		sql = sql.append(" order by def.area,def.area_id");
		return super.queryListBysql(sql.toString(), StoreDataView.class);
	}

	@Override
	public StoreDataView queryByUuid(String uuid) {
		StringBuilder sql = new StringBuilder("");
		 sql = sql.append("SELECT def.area,def.area_id,da.* FROM hs_store_def def")
				 .append(" LEFT JOIN hs_store_data da")
				 .append(" ON def.uuid = da.uuid ");
		if(StringUtils.isNotEmpty(uuid)) {
			sql = sql.append(" where def.uuid = '"+uuid+"'");
		}
		List<StoreDataView> lists = super.queryListBysql(sql.toString(), StoreDataView.class);
		if(lists != null && lists.size() ==1){
			return lists.get(0);
		}
		return null;
	}

}
