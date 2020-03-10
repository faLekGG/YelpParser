package com.varteq;

import com.varteq.config.AppConfig;
import com.varteq.service.ContractorsService;
import com.varteq.service.impl.ContractorsServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class YelpParserApplication {
  public static void main(String[] args) throws InterruptedException {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    ContractorsService contractorsService = ctx.getBean(ContractorsServiceImpl.class);
    contractorsService.saveParsedInformation();
  }
}
