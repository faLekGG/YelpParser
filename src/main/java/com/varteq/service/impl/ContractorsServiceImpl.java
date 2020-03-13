package com.varteq.service.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.varteq.constants.OtherConfigurationsOfApp;
import com.varteq.domain.Contractor;
import com.varteq.service.ContractorInfoParser;
import com.varteq.service.ContractorsService;
import com.varteq.service.LinksParser;
import com.varteq.service.MongoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractorsServiceImpl implements ContractorsService {

  @Value("${mongo.database}")
  private String mongoDatabase;

  @Value("${mongo.collection}")
  private String mongoCollection;

  private final MongoClient mongoClient;
  private final ContractorInfoParser<Contractor> contractorInfoParser;
  private final LinksParser<List<String>> linksParser;
  private final MongoService<Contractor> mongoService;

  @Override
  public void processParsedInformation() {
    createCollectionIfNotExist();
    List<String> listOfLinks = linksParser.parseData(OtherConfigurationsOfApp.INITIAL_STEP,
        OtherConfigurationsOfApp.LAST_STEP, OtherConfigurationsOfApp.STEP);
    for (String link : listOfLinks) {
      Contractor contractor = contractorInfoParser.parseData(link);
      mongoService.saveContractor(contractor);
    }
  }

  private void createCollectionIfNotExist() {
    MongoDatabase yelp = mongoClient.getDatabase(mongoDatabase);
    if (!mongoService.collectionExists(mongoCollection, yelp)) {
      log.debug("Create collection if not exists");
      yelp.createCollection(mongoCollection);
    }
  }
}
