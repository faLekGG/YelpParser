package com.varteq.service;

import com.varteq.BaseTestConfig;
import com.varteq.dao.ContractorsRepository;
import com.varteq.documents.DocumentFactory;
import com.varteq.domain.Contractor;
import com.varteq.service.impl.MongoContractorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class MongoContractorServiceImplTest extends BaseTestConfig {

  private ContractorsRepository contractorsRepository;
  private MongoService<Contractor> mongoService;
  private Contractor contractor;

  @Before
  public void init() {
    contractor = DocumentFactory.buildContractor();
    contractorsRepository = Mockito.mock(ContractorsRepository.class);
    mongoService = Mockito.mock(MongoContractorServiceImpl.class);
  }

  @Test
  public void test_saveParsedResultsToMongo() {
    Mockito.when(contractorsRepository.findContractorByName(ArgumentMatchers.anyString()))
        .thenReturn(contractor);

    mongoService.saveContractor(contractor);

    Mockito.verify(mongoService, Mockito.times(1))
        .saveContractor(contractor);
  }
}
