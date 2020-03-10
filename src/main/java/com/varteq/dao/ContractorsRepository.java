package com.varteq.dao;

import com.varteq.domain.Contractor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorsRepository extends MongoRepository<Contractor, String> {
  Contractor findContractorByName(String name);
}
