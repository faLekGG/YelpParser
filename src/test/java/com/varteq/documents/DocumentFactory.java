package com.varteq.documents;

import com.varteq.domain.Contacts;
import com.varteq.domain.Contractor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DocumentFactory {
  public static Contractor buildContractor() {
    Contractor contractor = new Contractor();
    contractor.setName("Mocked_name");
    contractor.setDescription("Mocked_name");
    contractor.setRating(4.5);
    contractor.setName("Mocked_name");
    contractor.setContacts(buildContacts());
    return contractor;
  }

  public static Contacts buildContacts() {
    Contacts contacts = new Contacts();
    contacts.setPhoneNumber("Mocked_number");
    contacts.setStreetAddress("Mocked_address");
    contacts.setWebsite("Mocked_website");
    return contacts;
  }
}
