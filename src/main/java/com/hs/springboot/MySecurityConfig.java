package com.hs.springboot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.hs.springboot.entity.Menu;
import com.hs.springboot.service.MenuService;
import com.hs.springboot.service.impl.JdbcUserAuthenticationService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled=true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;
	@Autowired
	private JdbcUserAuthenticationService userDetailsService;
	@Autowired
	private MenuService menuService;;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/js/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
					.anyRequest().authenticated()
					.and()
				.authenticationProvider(daoAuthenticationProvider())
				.formLogin()
					.loginProcessingUrl("/login")
					.loginPage("/in_page").permitAll()
					.successHandler(authenticationSuccessHandler())
					.failureHandler(authenticationFailureHandler())
				.and().logout()
				    .logoutUrl("/logout")
				    .logoutSuccessUrl("/in_page").permitAll()
				    .invalidateHttpSession(true)
				    .clearAuthentication(true)
				.and().headers().frameOptions().disable();
	}
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler();
		authenticationSuccessHandler.setAlwaysUseDefaultTargetUrl(true);
		authenticationSuccessHandler.setDefaultTargetUrl("/#");// 直接返回success  json时使用
		return authenticationSuccessHandler;
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		SimpleUrlAuthenticationFailureHandler authenticationFailureHandler = new SimpleUrlAuthenticationFailureHandler();
		authenticationFailureHandler.setDefaultFailureUrl("/#");// 直接返回faile  json时使用
		return authenticationFailureHandler;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
		providers.add(daoAuthenticationProvider());
		ProviderManager providerManager = new ProviderManager(providers);
		providerManager.setEraseCredentialsAfterAuthentication(false);
		return providerManager;
	}
	@Bean
	public FilterSecurityInterceptor filterSecurityInterceptor() {
		RoleVoter roleVoter = new RoleVoter();
		roleVoter.setRolePrefix("");
		
		@SuppressWarnings("rawtypes")
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
		decisionVoters.add(roleVoter);
		
		FilterSecurityInterceptor urlSecurityInterceptor = new FilterSecurityInterceptor();
		urlSecurityInterceptor.setAuthenticationManager(authenticationManager());
		urlSecurityInterceptor.setAccessDecisionManager(new AffirmativeBased(decisionVoters));
		urlSecurityInterceptor.setObserveOncePerRequest(false);
		urlSecurityInterceptor.setSecurityMetadataSource(defaultFilterInvocationSecurityMetadataSource());
		return urlSecurityInterceptor;
	}
	
	@SuppressWarnings("rawtypes")
	@Bean
	public FilterInvocationSecurityMetadataSource defaultFilterInvocationSecurityMetadataSource() {
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();;
		Iterator i$;
		Entry entry;
		
		Map<String, String> resourceMap = new LinkedHashMap<String, String>();
		List<Map<String, Object>> remenus = getRoleUrl();//this.roleDao.queryAccessAuthority();
		
		if (!CollectionUtils.isEmpty(remenus)) {
			for (Map<String, Object> map : remenus) {
				String role = (String) map.get("ROLE_CODE");
				List<Menu> urls = (List<Menu>) map.get("URL");
				if (!StringUtils.isEmpty(urls) && !StringUtils.isEmpty(role)) {
					for (Menu menu : urls) {
						String url =menu.getUrl();
						url = url + "*";
						if (resourceMap.containsKey(url)) {
							String value = resourceMap.get(url);
							resourceMap.put(url, value + "," + role);
						} else {
							resourceMap.put(url, role);
						}
					}
					
				}
			}
		}
		i$ = resourceMap.entrySet().iterator();

		while (i$.hasNext()) {
			entry = (Entry) i$.next();
			List list = SecurityConfig.createListFromCommaDelimitedString((String) entry.getValue());
			requestMap.put(new AntPathRequestMatcher((String) entry.getKey()), list);
		}
		return new DefaultFilterInvocationSecurityMetadataSource(requestMap);
	}
	
	private List<Map<String, Object>> getRoleUrl() {
		List<Map<String, Object>> list = new ArrayList<>();
		//TODO 配置权限
		Map map1 = new HashMap<>();
		map1.put("ROLE_CODE", "ROLE_ADMIN");
		List<Menu> adminm = menuService.queryMenuByRole("ROLE_ADMIN");
		map1.put("URL", adminm);
		
		list.add(map1);
		
		Map map2 = new HashMap<>();
		map2.put("ROLE_CODE", "ROLE_WAREHOUSE");
		List<Menu> warehouse = menuService.queryMenuByRole("ROLE_WAREHOUSE");
		map2.put("URL", warehouse);
		list.add(map2);
		
		Map map3 = new HashMap<>();
		map3.put("ROLE_CODE", "ROLE_LEADER");
		List<Menu> leader = menuService.queryMenuByRole("ROLE_LEADER");
		map3.put("URL", leader);
		list.add(map3);
		
		
		return list;
	}
	
	@Bean
	public AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setHideUserNotFoundExceptions(false);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(); 
		return NoOpPasswordEncoder.getInstance(); 
	}
	
}
