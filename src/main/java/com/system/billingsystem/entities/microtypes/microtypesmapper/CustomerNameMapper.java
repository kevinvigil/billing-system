package com.system.billingsystem.entities.microtypes.microtypesmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.billingsystem.entities.microtypes.names.CustomerName;
import org.jooq.JSONB;

public class CustomerNameMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static JSONB toJson(CustomerName customerName) {
        try{
            return JSONB.jsonb(mapper.writeValueAsString(customerName));
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public static CustomerName toDomain(JSONB json) {
        try {
            return mapper.readValue(json.data(), CustomerName.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public static CustomerName toDomain(String json) {
        try {
            return mapper.readValue(json, CustomerName.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
