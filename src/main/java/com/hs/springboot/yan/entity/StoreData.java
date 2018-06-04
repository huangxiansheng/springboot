package com.hs.springboot.yan.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *@描述 
 *@日期 2018年6月4日上午9:56:11
 *@作者 vpadmin
 */
public class StoreData implements Serializable{

	private String uuid;
	private String smokeId;
	private Integer smokeNumber;
	private Date createDate;
	private Date storeTime;
	private String storeUser;
	
	public Integer getSmokeNumber() {
		return smokeNumber;
	}
	public void setSmokeNumber(Integer smokeNumber) {
		this.smokeNumber = smokeNumber;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getSmokeId() {
		return smokeId;
	}
	public void setSmokeId(String smokeId) {
		this.smokeId = smokeId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getStoreTime() {
		return storeTime;
	}
	public void setStoreTime(Date storeTime) {
		this.storeTime = storeTime;
	}
	public String getStoreUser() {
		return storeUser;
	}
	public void setStoreUser(String storeUser) {
		this.storeUser = storeUser;
	}
	
}
