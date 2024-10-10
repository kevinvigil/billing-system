package com.system.billingsystem.entities.microtypes.ids;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CompanyId extends BaseId{
    @JsonCreator
    public CompanyId(@JsonProperty("value") UUID companyId) {
        super(companyId);
    }
}
