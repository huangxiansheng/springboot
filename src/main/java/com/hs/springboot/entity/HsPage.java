package com.hs.springboot.entity;

import java.io.Serializable;
import java.util.List;

/**
 *@描述 
 *@日期 2018年6月4日下午3:03:16
 *@作者 vpadmin
 */
public class HsPage implements Serializable{
	private int page;//第几页
	private int rows;//rows表示每页 多少行
	
	private String sort;//排序字段
	private String order;//排序方法
	
	private List resultData;//展示的数据
	private int total;//返回结果，共多少条数据
	
	
	public int getFirstNum() {
		return (page-1)*rows+1;
	}
	public int getEndNum() {
		return page*rows;
	}
	
	public List getResultData() {
		return resultData;
	}
	public void setResultData(List resultData) {
		this.resultData = resultData;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
