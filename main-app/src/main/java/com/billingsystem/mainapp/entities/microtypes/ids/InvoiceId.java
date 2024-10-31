package com.billingsystem.mainapp.entities.microtypes.ids;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class InvoiceId extends BaseId{
    @JsonCreator
    public InvoiceId(@JsonProperty("value") UUID invoiceId) {
        super(invoiceId);
    }
}
