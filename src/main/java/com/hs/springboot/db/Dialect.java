package com.hs.springboot.db;

import org.apache.commons.lang3.StringUtils;

import com.hs.springboot.entity.HsPage;

/**
 * @描述
 * @日期 2018年6月4日下午3:38:30
 * @作者 vpadmin
 */
public abstract class Dialect {
	public static final String PAGE_QUERY_STRING = "select * from (select temp_.* from (%s) as temp_)  as mytemp_  limit %s , %s";
	/**
	 * 统计sql的模板，mysql的
	 */
	public static final String COUNT_QUERY_STRING = "select count(*) from (%s) as mytemp_";

	protected boolean isOrderBy(String sql) {
		return StringUtils.contains(sql, "order by");
	}

	/**
	 * 获取统计的sql
	 */
	public String getCountString(String sql) {
		if (isOrderBy(sql)) {
			return String.format(COUNT_QUERY_STRING, new Object[] { StringUtils.substringBefore(sql, "order by") });
		}
		return String.format(COUNT_QUERY_STRING, new Object[] { sql });
	}

	public String getPageSql(String sql, HsPage page) {
		int first = page.getFirstNum()-1;
		return String.format(PAGE_QUERY_STRING, sql, first >= 0 ? first : 0, page.getRows());
	}
}
