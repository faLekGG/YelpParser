package com.varteq.service;

import com.varteq.service.impl.CollectLinksParserImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

public class CollectLinksParserImplTest {
  private LinksParser<List<String>> linksParser;
  private Document document;

  @Before
  public void init() {
    try {
      document = Jsoup.connect("https://www.yelp.com").get();
    } catch (IOException e) {
      e.printStackTrace();
    }
    linksParser = new CollectLinksParserImpl();
  }

  @Test
  public void test_parseLinksFromYelpWebsite() {
    Elements elements = new Elements();
    elements.add(new Element("<a href='biz/ponce-builders-chicago'>"));
    Mockito.when(document.getElementsByClass("abcd")).thenReturn(elements);
//    Assert.assertEquals(linksParser.parseData().size(), elements.size());
  }
}
