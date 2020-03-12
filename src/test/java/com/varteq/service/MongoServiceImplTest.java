package com.varteq.service;

import com.varteq.dao.ContractorsRepository;
import com.varteq.documents.DocumentFactory;
import com.varteq.domain.Contractor;
import com.varteq.service.impl.MongoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class MongoServiceImplTest {
  @Mock
  private ContractorsRepository contractorsRepository;

  @Mock
  private MongoService<Contractor> mongoService;

  /*@Before
  public void init() {
    mongoService = new MongoServiceImpl(contractorsRepository);
  }*/

  @Test
  public void test_saveParsedResultsToMongo() {
    Contractor contractor = DocumentFactory.buildContractor();
    Mockito.when(contractorsRepository.findContractorByName(contractor.getName()))
        .thenReturn(contractor);

    Mockito.doNothing().doThrow(new Exception("asd"))
        .when(contractorsRepository).save(contractor);

    Mockito.doNothing()
        .when(mongoService).saveContractor(contractor);

    Mockito.verify(mongoService, Mockito.times(1))
        .saveContractor(contractor);
  }
}
