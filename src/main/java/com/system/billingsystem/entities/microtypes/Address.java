package com.system.billingsystem.entities.microtypes;

import com.system.billingsystem.entities.microtypes.microtypesmapper.AddressMapper;
import lombok.Data;
import org.jooq.JSONB;

@Data
public class Address {

    private String country;
    private String state;
    private String city;
    private String zip;

    public Address(String country, String city, String state, String zip) {
        this.country = country;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Address(String country, String city, String state) {
        this.country = country;
        this.city = city;
        this.state = state;
    }

    public Address() {}

    @Override
    public String toString(){
        return  "{country: "+country+", state: "+state+", city: "+city+", zip: "+zip+"}";
    }
}
