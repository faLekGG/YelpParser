package com.varteq.service.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.varteq.dao.ContractorsRepository;
import com.varteq.domain.Contractor;
import com.varteq.service.MongoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MongoServiceImpl implements MongoService<Contractor> {

  @Value("${mongo.database}")
  private String mongoDatabase;

  @Value("${mongo.collection}")
  private String mongoCollection;

  private final MongoClient mongoClient;
  private final ContractorsRepository contractorsRepository;

  @Override
  public void configureAndSave(final Contractor contractor) {
    /*MongoDatabase yelp = mongoClient.getDatabase(mongoDatabase);

    if (!collectionExists(mongoCollection, yelp)) {
      yelp.createCollection(mongoCollection);
    }*/

    if (Objects.isNull(contractorsRepository.findContractorByName(contractor.getName()))) {
      contractorsRepository.save(contractor);
    }
  }

  private boolean collectionExists(final String collectionName, final MongoDatabase mongo) {
    MongoIterable<String> collectionNames = mongo.listCollectionNames();
    for (final String name : collectionNames) {
      if (name.equalsIgnoreCase(collectionName)) {
        return true;
      }
    }
    return false;
  }
}
