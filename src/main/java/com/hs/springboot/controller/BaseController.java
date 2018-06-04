package com.hs.springboot.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hs.springboot.entity.HsPage;
import com.hs.springboot.util.MathUtil;

/**
 *@描述 
 *@日期 2018年6月4日下午3:06:04
 *@作者 vpadmin
 */
public class BaseController {
	public HsPage getPage(HttpServletRequest request) {
		HsPage hsPage = new HsPage();
		hsPage.setPage(MathUtil.value(request.getParameter("page"), 1));
		hsPage.setRows(MathUtil.value(request.getParameter("rows"), 10));
		hsPage.setSort(request.getParameter("sort"));
		hsPage.setOrder(request.getParameter("order"));
		return hsPage;
	}
	
	/***
	 * 描述：根据request获取参数map 对象是String
	 * 
	 * @author 黄松
	 * @param request
	 * @return Map<String, String>
	 */
	protected Map<String, String> getRequestParams(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> enume = request.getParameterNames();
		while (enume.hasMoreElements()) {
			String paramName = enume.nextElement();
			String value = request.getParameter(paramName);
			String[] values = request.getParameterValues(paramName);
			if (values.length > 1) {
				String va = values[0];
				for (int i = 1; i < values.length; i++) {
					va = va + "," + values[i];
				}
				value = va;
			}
			params.put(paramName, value);
		}
		return params;
	}
}
