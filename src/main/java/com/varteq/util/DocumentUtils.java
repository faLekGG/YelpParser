package com.varteq.util;

import com.varteq.constants.UrlConstants;
import com.varteq.constants.UrlOptionsForSearch;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Slf4j
@UtilityClass
public class DocumentUtils {

  @Value("${yelp_url}")
  private String url;

  public static Document getDocumentToCollectLinks(int i) throws IOException {
    String composedUrlForParsing = String.format(UrlConstants.COLLECT_LINKS_URL,
        url, UrlOptionsForSearch.CFLT, UrlOptionsForSearch.LOCATION, i);
    log.debug("Composed url is {}", composedUrlForParsing);
    return Jsoup.connect(composedUrlForParsing).get();
  }

  public static Document getDocumentNumberOfPages() throws IOException {
    String composedUrlForParsingNop = String.format(UrlConstants.COLLECT_NUMBER_OF_PAGES_URL,
        url, UrlOptionsForSearch.CFLT, UrlOptionsForSearch.LOCATION);
    log.debug("url for parsing number of pages: {}", composedUrlForParsingNop);
    return Jsoup.connect(composedUrlForParsingNop).get();
  }

  public static Document getDocumentToParseContractorInfo(String link) throws IOException {
    String composedUrlForParsingContractor = String.format(UrlConstants.CONTRACTOR_URL, url, link);
    log.debug("url for parsing contractor: {}", composedUrlForParsingContractor);
    return Jsoup.connect(composedUrlForParsingContractor).get();
  }
}
