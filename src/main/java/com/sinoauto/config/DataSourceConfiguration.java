package com.sinoauto.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableTransactionManagement
@Configuration
public class DataSourceConfiguration {

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Bean
	public DataSource primaryDataSource() {

		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driverClassName);
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setMaximumPoolSize(20);
		hikariConfig.setMinimumIdle(20);
		hikariConfig.setIdleTimeout(240000);
		hikariConfig.setConnectionTimeout(10000);
		hikariConfig.setConnectionTestQuery("select 1");
		hikariConfig.setPoolName("SPRINGHIKARICP");
		hikariConfig.setMaxLifetime(240000);

		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}

}
