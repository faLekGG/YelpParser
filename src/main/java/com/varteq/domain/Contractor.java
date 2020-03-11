package com.varteq.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@ToString
@Document(collection = "yelpcollection")
public class Contractor {
  @MongoId
  private String id;
  private String name;
  private Double rating;
  private String description;
  private Contacts contacts;
}
