package com.varteq.service;

public interface LinksParser<T>{
  T parseData(int start, int last, int step);
}
