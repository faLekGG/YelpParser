package com.varteq.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LinkToContractor {

    @Id
    private String linkId;
    private String link;
}
