package com.varteq.service;

import com.varteq.BaseTestConfig;
import com.varteq.service.impl.CollectLinksParserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class CollectLinksParserImplTest extends BaseTestConfig {

  private LinksParser<List<String>> linksParser;

  @Before
  public void init() {
    linksParser = Mockito.mock(CollectLinksParserImpl.class);
  }

  @Test
  public void test_parseLinksFromYelpWebsite() {
    List<String> expectedListOfLinks = Collections.singletonList("asdasd");

    Mockito
        .when(linksParser.parseData(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt()))
        .thenReturn(expectedListOfLinks);

    List<String> actualOfLinks = linksParser.parseData(0, 0, 20);

    Mockito
        .verify(linksParser)
        .parseData(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());

    Assert.assertEquals(expectedListOfLinks.size(), actualOfLinks.size());
  }
}
