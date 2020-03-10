package com.varteq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.varteq")
@PropertySource("classpath:application.properties")
public class AppConfig {

}
