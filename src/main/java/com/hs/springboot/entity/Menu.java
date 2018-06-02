package com.hs.springboot.entity;
/**
 *@描述 
 *@日期 2018年6月1日上午11:12:39
 *@作者 vpadmin
 */

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable{

	private String id ;
	private String url ;
	private String text;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
