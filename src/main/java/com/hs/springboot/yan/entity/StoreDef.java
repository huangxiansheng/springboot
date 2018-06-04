package com.hs.springboot.yan.entity;

import java.io.Serializable;

/**
 *@描述 
 *@日期 2018年6月4日上午9:55:03
 *@作者 vpadmin
 */
public class StoreDef implements Serializable{

	private String uuid;
	private String area;
	private String areaId;
	
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
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	
}
