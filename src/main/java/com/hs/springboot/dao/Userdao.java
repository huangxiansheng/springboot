package com.hs.springboot.dao;

import java.util.List;
import java.util.Map;

/**
 *@描述 
 *@日期 2018年5月31日下午12:55:35
 *@作者 vpadmin
 */
public interface Userdao {

	List queryList(Map<String,String> map);
	
}
