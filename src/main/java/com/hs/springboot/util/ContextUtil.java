package com.hs.springboot.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *@描述 
 *@日期 2018年6月1日上午9:57:13
 *@作者 vpadmin
 */
public class ContextUtil {
	
	public static User getUserInfo() {
		User userinfo = null;
		Authentication authentication = getAuthentication();
		if (authentication != null) {
			Object principle = authentication.getPrincipal();
			if (principle instanceof User) {
				userinfo = (User) principle;
			}

			return userinfo;
		} else {
			throw new RuntimeException("无法获取当前用户的认证信息");
		}
	}

	protected static Authentication getAuthentication() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return securityContext.getAuthentication();
	}
}
