package com.hs.springboot.yan.entity;

import java.util.Date;

/**
 *@描述 
 *@日期 2018年6月4日上午9:58:32
 *@作者 vpadmin
 */
public class StoreHis extends StoreData {

	private Date outTime;
	private String outUser;
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public String getOutUser() {
		return outUser;
	}
	public void setOutUser(String outUser) {
		this.outUser = outUser;
	}
	
	
}
