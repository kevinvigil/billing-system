package com.billingsystem.mainapp.entities.microtypes.microtypesmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.billingsystem.mainapp.entities.microtypes.Address;
import org.jooq.JSONB;

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
