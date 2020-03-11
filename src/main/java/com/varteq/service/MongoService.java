package com.varteq.service;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public interface MongoService<T> {
  void saveContractor(final T data);

  default boolean collectionExists(final String collectionName, final MongoDatabase mongo) {
    MongoIterable<String> collectionNames = mongo.listCollectionNames();
    for (final String name : collectionNames) {
      if (name.equalsIgnoreCase(collectionName)) {
        return true;
      }
    }
    return false;
  }
}
