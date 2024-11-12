package com.billingsystem.mainapp.entities.microtypes.ids;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CompanyId extends Id {
    @JsonCreator
    public CompanyId(@JsonProperty("value") UUID companyId) {
        super(companyId);
    }
}
