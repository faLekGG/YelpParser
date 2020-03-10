package com.varteq.service.impl;

import com.varteq.domain.Contacts;
import com.varteq.domain.Contractor;
import com.varteq.service.ContractorInfoParser;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
public class ContractorInfoParserImpl implements ContractorInfoParser<Contractor> {

  @Value("${contractor_url}")
  private String url;

  @Override
  public Contractor parseData(String link) {
    Contractor contractor = new Contractor();
    try {
      Document doc = Jsoup.connect(url + link).get();
      contractor.setName(getContractorsName(doc));
      contractor.setRating(getContractorsRating(doc));
      contractor.setDescription(getContractorsDescription(doc));
      contractor.setContacts(getContractorsContacts(doc));
    } catch (IOException e) {
      log.error("Connection provided by JSoup was interrupted ", e);
    }
    return contractor;
  }

  private String getContractorsName(Document document) {
    Element element = document.getElementsByClass("lemon--h1__373c0__2ZHSL heading--h1__373c0__1VUMO "
        + "heading--no-spacing__373c0__1PzQP heading--inline__373c0__1F-Z6").first();

    return Objects.nonNull(element) ? element.text() : "";
  }

  private Contacts getContractorsContacts(Document document) {
    Contacts contacts = new Contacts();
    Element websiteParsed = document.getElementsByAttributeValue("rel", "noopener").first();
    contacts.setWebsite(Objects.nonNull(websiteParsed) ? websiteParsed.text() : "website");
    Element phoneNumberParsed = document.getElementsByAttributeValue("itemprop", "telephone").first();
    contacts.setPhoneNumber(Objects.nonNull(phoneNumberParsed) ? phoneNumberParsed.text() : "phoneNumber");
    Element streetAddressParsed = document.getElementsByAttributeValue("itemprop", "streetAddress").first();
    contacts.setStreetAddress(Objects.nonNull(streetAddressParsed) ? streetAddressParsed.text() : "address");
    return contacts;
  }

  private double getContractorsRating(Document document) {
    String parsedRating = document.getElementsByClass("lemon--div__373c0__1mboc arrange__373c0__UHqhV "
        + "gutter-6__373c0__zqA5A vertical-align-middle__373c0__2TQsQ u-space-b1 border-color--default__373c0__2oFDT")
        .select("div.lemon--div__373c0__1mboc")
        .attr("aria-label");

    return Double.parseDouble(parsedRating.trim().substring(0, parsedRating.indexOf("star")-1));
  }

  private String getContractorsDescription(Document document) {
    Element descriptionParsed = document.getElementsByAttributeValue("property", "og:description").first();
    return descriptionParsed.attr("content").substring(13);
  }
}
