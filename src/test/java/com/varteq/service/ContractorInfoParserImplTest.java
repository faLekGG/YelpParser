package com.varteq.service;

import com.varteq.BaseTestConfig;
import com.varteq.documents.DocumentFactory;
import com.varteq.domain.Contractor;
import com.varteq.service.impl.ContractorInfoParserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ContractorInfoParserImplTest extends BaseTestConfig {

  private ContractorInfoParser<Contractor> contractorInfoParser;
  private Contractor expectedContractor;

  @Before
  public void init() {
    contractorInfoParser = Mockito.mock(ContractorInfoParserImpl.class);
    expectedContractor = DocumentFactory.buildContractor();
  }

  @Test
  public void test_parseContractorInfo() {
    Mockito
        .when(contractorInfoParser.parseData(ArgumentMatchers.anyString()))
        .thenReturn(expectedContractor);

    Contractor actualContractor = contractorInfoParser.parseData(expectedContractor.getName());

    Mockito
        .verify(contractorInfoParser)
        .parseData(ArgumentMatchers.anyString());

    Assert.assertNotNull(actualContractor);
    Assert.assertEquals(expectedContractor.getName(), actualContractor.getName());
    Assert.assertEquals(expectedContractor.getRating(), actualContractor.getRating());
    Assert.assertEquals(expectedContractor.getContacts().getWebsite(), actualContractor.getContacts().getWebsite());
  }
}
