package com.modeln.fxr.main.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class BatchDataSourceConfiguration {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "jdbc.batch.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "jdbc.batch.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "jdbc.batch.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "jdbc.batch.username";

	@Resource
	private Environment env;

	@Bean
	public DataSource infraDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

		return dataSource;
	}
}
