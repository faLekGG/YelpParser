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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractorInfoParserImpl implements ContractorInfoParser<Contractor> {

  @Value("${yelp_url}")
  private String url;

  @Override
  public Contractor parseData(final String link) {
    Contractor contractor = new Contractor();
    try {
      Document doc = DocumentUtils.getDocumentToParseContractorInfo(url, link);
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
    Element websiteParsed = document
        .getElementsByAttributeValue(ContractorInfoDom.WEBSITE_ATTR_KEY, ContractorInfoDom.WEBSITE_ATTR_VALUE)
        .first();
    contacts.setWebsite(Objects.nonNull(websiteParsed) ? websiteParsed.text() : "");
    Element phoneNumberParsed = document
        .getElementsByAttributeValue(ContractorInfoDom.PHONE_ADDRESS_ATTR_KEY, ContractorInfoDom.PHONE_ATTR_VALUE)
        .first();
    contacts.setPhoneNumber(Objects.nonNull(phoneNumberParsed) ? phoneNumberParsed.text() : "");
    Element streetAddressParsed = document
        .getElementsByAttributeValue(ContractorInfoDom.PHONE_ADDRESS_ATTR_KEY, ContractorInfoDom.ADDRESS_ATTR_VALUE)
        .first();
    contacts.setStreetAddress(Objects.nonNull(streetAddressParsed) ? streetAddressParsed.text() : "");
    return contacts;
  }

  private double getContractorsRating(final Document document) {
    String parsedRating = document.getElementsByClass(ContractorInfoDom.CONTRACTOR_RATING)
        .select(ContractorInfoDom.CONTRACTOR_RATING_SELECTOR)
        .attr(ContractorInfoDom.CONTRACTOR_RATING_ATTR_KEY);
    return parsedRating.isEmpty() ? 0 :
        Double.parseDouble(parsedRating.trim().substring(0, parsedRating.indexOf(ContractorInfoDom.RATING_INDEX) - 1));
  }

  private String getContractorsDescription(final Document document) {
    Element descriptionParsed = document
        .getElementsByAttributeValue(ContractorInfoDom.DESC_KEY, ContractorInfoDom.DESC_VALUE)
        .first();
    return descriptionParsed.attr(ContractorInfoDom.DESC_PARSED_KEY).substring(13);
  }
}
