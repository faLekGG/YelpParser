package com.varteq.documents;

import com.varteq.domain.Contacts;
import com.varteq.domain.Contractor;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

@UtilityClass
public class DocumentFactory {
  public static Contractor buildContractor() {
    Contractor contractor = new Contractor();
    contractor.setName(RandomStringUtils.randomAlphanumeric(5));
    contractor.setDescription(RandomStringUtils.randomAlphanumeric(50));
    contractor.setRating(0 + (new Random().nextDouble() * (5)));
    contractor.setName(RandomStringUtils.randomAlphabetic(10));
    contractor.setContacts(buildContacts());
    return contractor;
  }

  public static Contacts buildContacts() {
    Contacts contacts = new Contacts();
    contacts.setPhoneNumber(RandomStringUtils.randomAlphabetic(10));
    contacts.setStreetAddress(RandomStringUtils.randomAlphabetic(10));
    contacts.setWebsite(RandomStringUtils.randomAlphabetic(10));
    return contacts;
  }
}
