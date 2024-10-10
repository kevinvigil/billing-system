package com.system.billingsystem.entities.microtypes.ids;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CustomerId extends BaseId{
    @JsonCreator
    public CustomerId(@JsonProperty ("value") UUID customerId) {
        super(customerId);
    }
}
