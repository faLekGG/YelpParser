package com.varteq.service;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoDatabaseImpl;
import com.varteq.BaseTestConfig;
import com.varteq.documents.DocumentFactory;
import com.varteq.domain.Contractor;
import com.varteq.service.impl.CollectLinksParserImpl;
import com.varteq.service.impl.ContractorInfoParserImpl;
import com.varteq.service.impl.ContractorsServiceImpl;
import com.varteq.service.impl.MongoContractorServiceImpl;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContractorsServiceImplTest extends BaseTestConfig {

  private ContractorInfoParser<Contractor> contractorInfoParser;
  private LinksParser<List<String>> linksParser;
  private MongoService<Contractor> mongoService;
  private ContractorsService contractorsService;
  private MongoDatabase mongoDatabase;

  @Before
  public void init() {
    mongoService = Mockito.mock(MongoContractorServiceImpl.class);
    linksParser = Mockito.mock(CollectLinksParserImpl.class);
    contractorInfoParser = Mockito.mock(ContractorInfoParserImpl.class);
    contractorsService = Mockito.mock(ContractorsServiceImpl.class);
    mongoDatabase = Mockito.mock(MongoDatabaseImpl.class);
  }

  @SneakyThrows
  @Test
  public void test_processParsedInformation() {
    Mockito
        .when(mongoService.collectionExists(eq(ArgumentMatchers.anyString()), mongoDatabase))
        .thenReturn(true);

    Mockito
        .when(linksParser.parseData(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        .thenReturn(Collections.singletonList("mocked data"));

    Mockito
        .when(contractorInfoParser.parseData(ArgumentMatchers.anyString()))
        .thenReturn(DocumentFactory.buildContractor());

    contractorsService.processParsedInformation();

    Mockito.verify(contractorsService, Mockito.times(1))
        .processParsedInformation();
  }
}
