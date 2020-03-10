package com.varteq.service;

public interface MongoService<T> {
  void configureAndSave(final T data);
}
