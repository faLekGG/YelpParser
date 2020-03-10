package com.varteq.service.impl;

import com.varteq.domain.Contractor;
import com.varteq.service.ContractorInfoParser;
import com.varteq.service.MongoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
public class ContractorTask implements Runnable {

  private String link;

  @Autowired
  private ContractorInfoParser<Contractor> contractorInfoParser;

  @Autowired
  private MongoService<Contractor> mongoService;

  @Override
  public void run() {
    Contractor contractor = contractorInfoParser.parseData(link);
    mongoService.configureAndSave(contractor);
  }
}
