package com.system.billingsystem.entities.microtypes.microtypesmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.billingsystem.entities.microtypes.Phone;
import org.jooq.JSONB;

public class PhoneMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static JSONB toJson(Phone phone) {
        try {
            return JSONB.jsonb(mapper.writeValueAsString(phone));
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Phone toDomain(JSONB json) {
        try {
            return mapper.readValue(json.data(), Phone.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public static Phone toDomain(String json) {
        try {
            return mapper.readValue(json, Phone.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
