package com.system.billingsystem.entities.microtypes.ids;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class InvoiceProductId extends BaseId{
    @JsonCreator
    public InvoiceProductId(@JsonProperty("value") UUID invoiceProductId) {
        super(invoiceProductId);
    }
}
