package com.hs.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hs.springboot.db.Dialect;
import com.hs.springboot.db.MysqlDialect;

/**
 *@描述 
 *@日期 2018年5月15日上午9:19:50
 *@作者 vpadmin
 */
@SpringBootApplication
public class Application {
	
	@Bean
	public Dialect getDialect() {
		return new MysqlDialect();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
