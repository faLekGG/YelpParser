package com.varteq.service.impl;

import com.varteq.dao.ContractorsRepository;
import com.varteq.domain.Contractor;
import com.varteq.service.MongoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MongoServiceImpl implements MongoService<Contractor> {

  private final ContractorsRepository contractorsRepository;

  @Override
  public void saveContractor(final Contractor contractor) {
    if (contractorsRepository.findContractorByName(contractor.getName()) == null) {
      contractorsRepository.save(contractor);
    }
  }
}
