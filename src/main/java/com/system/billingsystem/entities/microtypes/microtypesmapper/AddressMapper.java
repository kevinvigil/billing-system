package com.system.billingsystem.entities.microtypes.microtypesmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.billingsystem.entities.microtypes.Address;
import org.jooq.JSONB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static JSONB toJson(Address address) {
        try{
            return JSONB.valueOf(mapper.writeValueAsString(address));
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public static Address toDomain(JSONB json) {
        try{
            return mapper.readValue(json.data(), Address.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public static Address toDomain(String json) {
        try{
            return mapper.readValue(json, Address.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
