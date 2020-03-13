package com.varteq;

import com.varteq.config.AppConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration(classes = AppConfig.class)
@TestPropertySource("classpath:application-test.properties")
public class BaseTestConfig {

}
