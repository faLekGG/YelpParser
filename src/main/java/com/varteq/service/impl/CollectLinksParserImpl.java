package com.varteq.service.impl;

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

  @Value("${collect_links_url}")
  private String url;

  @Override
  public List<String> parseData() {
    List<String> listOfLinks = new ArrayList<>();
    try {
      //For some reason yelp doesn't allow to see results further than 50 page
      //In the case it begins work we need to use this formula
      //to get all seen results: int i = numberOfPages * 20
      //currently 50th page is a limit
      for (int i = 20; i >= 0; i -= 20) {
        Document doc = Jsoup.connect(url + i).get();
        Elements aElements = doc.getElementsByClass("lemon--a__373c0__IEZFH link__373c0__1G70M "
            + "link-color--inherit__373c0__3dzpk link-size--inherit__373c0__1VFlE");
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

  private Integer getNumberOfPages(Document document) throws IOException {
    Document doc = Jsoup.connect(url).get();
    log.debug("url for parsing number of pages: {}", url);
    Element element = doc.getElementsByClass("lemon--div__373c0__1mboc padding-b2__373c0__34gV1 "
        + "border-color--default__373c0__3-ifU text-align--center__373c0__2n2yQ")
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
