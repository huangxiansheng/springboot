package com.hs.springboot.intercept;

import java.util.Collection;
import java.util.LinkedHashMap;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 *@描述 
 *@日期 2018年5月31日下午2:35:33
 *@作者 vpadmin
 */
public class ResourceFilter extends DefaultFilterInvocationSecurityMetadataSource {

	public ResourceFilter(LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		super(requestMap);
	}

}
