package com.varteq.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.varteq.dao")
public class MongodbDataSourceConfig {

  @Value("${mongo.user}")
  private String mongoUser;

  @Value("${mongo.password}")
  private String mongoPassword;

  @Value("${mongo.database}")
  private String mongoDatabase;

  @Value("${mongo.host}")
  private String host;

  @Value("${mongo.port}")
  private Integer port;

  @Bean
  public MongoCredential mongoCredential() {
    return MongoCredential.createCredential(mongoUser, mongoDatabase, mongoPassword.toCharArray());
  }

  @Bean
  public MongoClientSettings mongoClientSettings() {
    return MongoClientSettings.builder()
        .credential(mongoCredential())
        .applyToClusterSettings(builder ->
            builder.hosts(Collections.singletonList(new ServerAddress(host, port))))
        .build();
  }

  @Bean
  public MongoClient mongoClient() {
    return MongoClients.create(mongoClientSettings());
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(mongoClient(), mongoDatabase);
  }
}
