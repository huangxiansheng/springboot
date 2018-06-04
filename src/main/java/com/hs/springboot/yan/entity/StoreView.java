package com.hs.springboot.yan.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *@描述 
 *@日期 2018年6月4日下午2:31:07
 *@作者 vpadmin
 */
//按区域查询，要根据  区域，烟id，生产日期，入库日期，汇总的视图
public class StoreView implements Serializable{
	private String uuid;
	private String area;
	private String smokeId;
	private Integer smokeNumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date storeTime;
	
	public Integer getSmokeNumber() {
		return smokeNumber;
	}
	public void setSmokeNumber(Integer smokeNumber) {
		this.smokeNumber = smokeNumber;
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
}
