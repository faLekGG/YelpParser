package com.varteq.service;

import com.varteq.config.AppConfig;
import com.varteq.service.impl.CollectLinksParserImpl;
import com.varteq.util.DocumentUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class CollectLinksParserImplTest {

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
