package com.modeln.fxr.main.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.modeln.fxr"})
@PropertySource("classpath:application.properties")
@Import({DataSourceConfiguration.class, BatchConfiguration.class, DroolsConfig.class})
public class AppConfiguration {

}
