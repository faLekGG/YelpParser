package com.varteq.service.impl;

import com.varteq.constants.CollectLinksDom;
import com.varteq.constants.UrlConstants;
import com.varteq.constants.UrlOptionsForSearch;
import com.varteq.service.LinksParser;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class CollectLinksParserImpl implements LinksParser<List<String>> {

  @Value("${yelp_url}")
  private String url;

  @Override
  public List<String> parseData(int start, int last, int step) {
    List<String> listOfLinks = new ArrayList<>();
    try {
      //For some reason yelp doesn't allow to see results further than 50 page
      //In the case it begins work we need to use this formula
      //to get all seen results: int i = numberOfPages * 20
      //currently 50th page is a limit
      for (int i = start; i >= last; i -= step) {
        String composedUrlForParsing = String.format(UrlConstants.COLLECT_LINKS_URL,
            url, UrlOptionsForSearch.CFLT, UrlOptionsForSearch.LOCATION, i);
        Document doc = Jsoup.connect(composedUrlForParsing).get();
        log.debug("Composed url is {}", composedUrlForParsing);
        Elements aElements = doc.getElementsByClass(CollectLinksDom.LOOK_UP_LINKS_BY_CLASS);
        aElements.stream()
            .map(el -> el.attr("href"))
            .filter(link -> link.startsWith("/biz"))
            .forEach(listOfLinks::add);
      }
    } catch (IOException e) {
      log.error("Connection provided by JSoup was interrupted ", e);
    }
    return listOfLinks;
  }

  private Integer getNumberOfPages() throws IOException {
    String composedUrlForParsingNop = String.format(UrlConstants.COLLECT_NUMBER_OF_PAGES_URL,
        url, UrlOptionsForSearch.CFLT, UrlOptionsForSearch.LOCATION);
    Document doc = Jsoup.connect(composedUrlForParsingNop).get();
    log.debug("url for parsing number of pages: {}", composedUrlForParsingNop);
    Element element = doc.getElementsByClass(CollectLinksDom.LOOK_UP_NUMBER_OF_PAGES)
        .tagName("span")
        .first();
    String parsedNumberOfPages = "";
    if (Objects.nonNull(element)) {
      parsedNumberOfPages = element.text().trim().substring(5);
    }
    int numberOfPages = Integer.parseInt(parsedNumberOfPages);
    log.debug("Number of pages for contractors: {}", numberOfPages);
    return numberOfPages;
  }
}
