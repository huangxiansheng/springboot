package com.hs.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment environment;
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/user/**");//哪些不验证
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeRequests()
					.antMatchers("/login/oauth2/**").permitAll()
					.anyRequest().authenticated()
					.and()
				.formLogin()
				.and().logout()
				    .logoutUrl("/logout")
				    .logoutSuccessUrl("/logoutSu").permitAll()
				    .invalidateHttpSession(true)
				    .clearAuthentication(true);
	}

}
