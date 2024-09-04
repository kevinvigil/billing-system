package com.system.billingsystem.entities.microtypes;

import lombok.Data;

@Data
public class Address {

    private String country;
    private String state;
    private String city;
    private String zip;

    public Address(String country, String state, String city, String zip) {
        this.country = country;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Address(String country, String state, String city) {
        this.country = country;
        this.city = city;
        this.state = state;
    }

    public Address() {}

    @Override
    public String toString(){
        return country+", "+state+", "+city+", "+zip;
    }
}
