package com.varteq.service.impl;

import com.varteq.domain.Contractor;
import com.varteq.service.ContractorInfoParser;
import com.varteq.service.ContractorsService;
import com.varteq.service.LinksParser;
import com.varteq.service.MongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractorsServiceImpl implements ContractorsService {

//  private final ContractorInfoParser<Contractor> contractorInfoParser;
  private final LinksParser<List<String>> linksParser;
  private final MongoService<Contractor> mongoService;


  @Override
  public void saveParsedInformation() {
    List<String> listOfLinks = linksParser.parseData();
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
//    List<ScheduledFuture<Contractor>> scheduledFutures = new ArrayList<>();
    for (String link : listOfLinks) {
      ContractorTask contractorTask = new ContractorTask();
      contractorTask.setLink(link);
      executorService.scheduleAtFixedRate(contractorTask, 0, 1, TimeUnit.SECONDS);
    }

  }
}
