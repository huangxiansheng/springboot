package com.hs.springboot.dao.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import com.hs.springboot.dao.BaseDao;
import com.hs.springboot.dao.GenericBeanRowMapper;
import com.hs.springboot.db.Dialect;
import com.hs.springboot.entity.HsPage;

/**
 *@描述 
 *@日期 2018年5月31日下午12:56:53
 *@作者 vpadmin
 */
public abstract class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK >{

	@Autowired
	public Dialect dialect;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	protected Class<T> persistentClass;
	
	public BaseDaoImpl(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	public List<T> queryListBysql(String sql) {
		return jdbcTemplate.query(sql, new GenericBeanRowMapper<T>(persistentClass));
	}
	
	public <E> List<E> queryListBysql(String sql,Class<E> clazz) {
		return jdbcTemplate.query(sql, new GenericBeanRowMapper<E>(clazz));
	}
	
	protected <E> HsPage queryForPageBySql(String sql, HsPage page,Class<E> clazz) {
		int total = page.getTotal();
		String countSql = dialect.getCountString(sql);
		total = queryForIntBySql(countSql);
		page.setTotal(total);

		List<E> content = total > 0 ? queryListBysql(dialect.getPageSql(sql, page),clazz)
				: Collections.<E>emptyList();
		
		HsPage rePage = new HsPage();
		rePage.setResultData(content);
		rePage.setPage(page.getPage());
		rePage.setRows(page.getRows());
		
		rePage.setOrder(page.getOrder());
		rePage.setSort(page.getSort());
		rePage.setTotal(total);
		return rePage;
	}
	protected HsPage queryForPageBySql(String sql, HsPage page) {
		int total = page.getTotal();
		String countSql = dialect.getCountString(sql);
		total = queryForIntBySql(countSql);
		page.setTotal(total);

		List<T> content = total > 0 ? queryListBysql(dialect.getPageSql(sql, page))
				: Collections.<T>emptyList();
		
		HsPage rePage = new HsPage();
		rePage.setResultData(content);
		rePage.setPage(page.getPage());
		rePage.setRows(page.getRows());
		
		rePage.setOrder(page.getOrder());
		rePage.setSort(page.getSort());
		rePage.setTotal(total);
		return rePage;
	}
	
	protected int queryForIntBySql(String sql) {
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	protected void insertOrUpdate(String sql,Object[] values) {
		jdbcTemplate.update(sql, values);
	}
	
}
