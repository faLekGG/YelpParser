package com.varteq.service;

public interface ContractorInfoParser<T> {
  T parseData(String link);
}
