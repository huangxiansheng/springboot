package com.hs.springboot.yan.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hs.springboot.dao.impl.BaseDaoImpl;
import com.hs.springboot.entity.HsPage;
import com.hs.springboot.yan.dao.StoreDao;
import com.hs.springboot.yan.entity.StoreData;
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
	public List<StoreDataView> queryStoreDate(String area,String smokeId) {
		StringBuilder sql = new StringBuilder("");
		 sql = sql.append("SELECT def.area,def.area_id,da.* FROM hs_store_def def")
				 .append(" LEFT JOIN hs_store_data da")
				 .append(" ON def.uuid = da.uuid where 1=1 ");
		if(StringUtils.isNotEmpty(area)) {
			sql = sql.append(" and def.area = '"+area+"'");
		}
		if(StringUtils.isNotEmpty(smokeId)) {
			sql = sql.append(" and da.smoke_Id = '"+smokeId+"'");
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

	@Override
	public List<StoreDef> queryListDefByarea(String area) {
		String sql = "select area,area_id from hs_store_def where area='"+area+"' order by area_id";
		return super.queryListBysql(sql, StoreDef.class);
	}

//	private String uuid;
//	private String smokeId;
//	private String tray;
//	private Integer smokeNumber;
//	@JsonFormat(pattern="yyyy-MM-dd")
//	private Date createDate;
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	private Date storeTime;
//	private String storeUser;
	@Override
	public void save(StoreData sd) {
		String sql ="insert into hs_store_data(uuid,smoke_Id,tray,smoke_Number,create_Date,store_Time,store_User) values(?,?,?,?,?,?,?)";
		Object[] values = {sd.getUuid(),sd.getSmokeId(),sd.getTray(),sd.getSmokeNumber(),sd.getCreateDate(),sd.getStoreTime(),sd.getStoreUser()};
		this.insertOrUpdate(sql, values);
	}

	@Override
	public void update(StoreData sd) {
		String sql ="update hs_store_data set smoke_Id = ?,tray=?,smoke_Number=?,create_Date=? where uuid =?";
		Object[] values = {sd.getSmokeId(),sd.getTray(),sd.getSmokeNumber(),sd.getCreateDate() ,sd.getUuid()};
		this.insertOrUpdate(sql, values);
	}

}
