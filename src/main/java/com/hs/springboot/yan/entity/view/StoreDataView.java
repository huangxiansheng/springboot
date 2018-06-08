package com.hs.springboot.yan.entity.view;

import com.hs.springboot.yan.entity.StoreData;

/**
 *@描述 
 *@日期 2018年6月8日上午9:30:23
 *@作者 vpadmin
 */
public class StoreDataView extends StoreData{
	private String area;
	private String areaId;
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
