package com.hs.springboot.util;

import javax.management.RuntimeErrorException;

/**
 *@描述 
 *@日期 2018年6月4日下午3:14:33
 *@作者 vpadmin
 */
public class MathUtil {
	public static Integer value(String o , Integer defaultv) {
		Integer v = new Integer(defaultv);
		try {
			v = Integer.valueOf(o);
		} catch (NumberFormatException e) {
		}
		return v;
	}
}
