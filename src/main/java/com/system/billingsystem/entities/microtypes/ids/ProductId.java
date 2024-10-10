package com.system.billingsystem.entities.microtypes.ids;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ProductId extends BaseId{
    @JsonCreator
    public ProductId(@JsonProperty("value") UUID productId) {
        super(productId);
    }
}
