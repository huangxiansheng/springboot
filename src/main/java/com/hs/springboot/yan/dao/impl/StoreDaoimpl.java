package com.hs.springboot.yan.dao.impl;

import org.springframework.stereotype.Repository;

import com.hs.springboot.dao.impl.BaseDaoImpl;
import com.hs.springboot.yan.dao.StoreDao;
import com.hs.springboot.yan.entity.StoreDef;

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

}
