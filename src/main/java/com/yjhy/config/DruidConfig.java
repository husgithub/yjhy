package com.yjhy.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.Arrays;

@Configuration
public class DruidConfig {

	@ConfigurationProperties(prefix="spring.druid")
	@Bean(initMethod="init",destroyMethod="close")
	public DruidDataSource dataSource(Filter statFilter) throws SQLException{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setProxyFilters(Arrays.asList(statFilter()));
		return dataSource;
	}
	
	@Bean
	public Filter statFilter(){
		StatFilter filter = new StatFilter();
		filter.setSlowSqlMillis(5000);
		filter.setLogSlowSql(true);
		filter.setMergeSql(true);
		return filter;
	}
	
	
	@Bean
	public ServletRegistrationBean servletRegistrationBean(){
		return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
	}
	
}
