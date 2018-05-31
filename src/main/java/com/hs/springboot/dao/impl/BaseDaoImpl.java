package com.hs.springboot.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.hs.springboot.dao.BaseDao;
import com.hs.springboot.dao.GenericBeanRowMapper;

/**
 *@描述 
 *@日期 2018年5月31日下午12:56:53
 *@作者 vpadmin
 */
public abstract class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK >{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	protected Class<T> persistentClass;
	
	public BaseDaoImpl(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	public List<T> queryListBysql(String sql) {
		Assert.hasText(sql);
		return jdbcTemplate.query(sql, new GenericBeanRowMapper<T>(persistentClass));
	}
	
}
