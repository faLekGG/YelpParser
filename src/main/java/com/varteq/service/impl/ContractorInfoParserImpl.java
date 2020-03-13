package com.varteq.service.impl;

import com.varteq.constants.ContractorInfoDom;
import com.varteq.domain.Contacts;
import com.varteq.domain.Contractor;
import com.varteq.service.ContractorInfoParser;
import com.varteq.util.DocumentUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractorInfoParserImpl implements ContractorInfoParser<Contractor> {

  @Override
  public Contractor parseData(final String link) {
    Contractor contractor = new Contractor();
    try {
      Document doc = DocumentUtils.getDocumentToParseContractorInfo(link);
      contractor.setName(getContractorsName(doc));
      contractor.setRating(getContractorsRating(doc));
      contractor.setDescription(getContractorsDescription(doc));
      contractor.setContacts(getContractorsContacts(doc));
      log.debug("The next contractor has been parsed: {}", contractor.toString());
    } catch (IOException e) {
      log.error("Connection provided by JSoup was interrupted ", e);
    }
    return contractor;
  }

  private String getContractorsName(final Document document) {
    Element element = document.getElementsByClass(ContractorInfoDom.CONTRACTOR_NAME).first();
    return Objects.nonNull(element) ? element.text() : "";
  }

  private Contacts getContractorsContacts(final Document document) {
    Contacts contacts = new Contacts();
    Element websiteParsed = document.getElementsByAttributeValue("rel", "noopener").first();
    contacts.setWebsite(Objects.nonNull(websiteParsed) ? websiteParsed.text() : "");
    Element phoneNumberParsed = document.getElementsByAttributeValue("itemprop", "telephone").first();
    contacts.setPhoneNumber(Objects.nonNull(phoneNumberParsed) ? phoneNumberParsed.text() : "");
    Element streetAddressParsed = document.getElementsByAttributeValue("itemprop", "streetAddress").first();
    contacts.setStreetAddress(Objects.nonNull(streetAddressParsed) ? streetAddressParsed.text() : "");
    return contacts;
  }

  private double getContractorsRating(final Document document) {
    String parsedRating = document.getElementsByClass(ContractorInfoDom.CONTRACTOR_RATING)
        .select("div.lemon--div__373c0__1mboc")
        .attr("aria-label");
    return Double.parseDouble(parsedRating.trim().substring(0, parsedRating.indexOf("star") - 1));
  }

  private String getContractorsDescription(final Document document) {
    Element descriptionParsed = document.getElementsByAttributeValue("property", "og:description").first();
    return descriptionParsed.attr("content").substring(13);
  }
}
