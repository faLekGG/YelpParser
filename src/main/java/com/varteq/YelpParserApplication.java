package com.varteq;

import com.varteq.config.AppConfig;
import com.varteq.constants.OtherConfigurationsOfApp;
import com.varteq.service.ContractorsService;
import com.varteq.service.impl.ContractorsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class YelpParserApplication {
  public static void main(String[] args){
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    ContractorsService contractorsService = ctx.getBean(ContractorsServiceImpl.class);
    ScheduledExecutorService executorService =
        Executors.newScheduledThreadPool(OtherConfigurationsOfApp.NUMBER_OF_THREADS);
    executorService.schedule(() -> {
      try {
        contractorsService.saveParsedInformation();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        log.error("Scheduler was interrupted ", e);
      }
    }, 1, TimeUnit.DAYS);
  }
}
