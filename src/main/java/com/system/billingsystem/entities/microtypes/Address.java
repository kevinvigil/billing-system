package com.system.billingsystem.entities.microtypes;

import lombok.Data;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        if (!Objects.equals(country, address.country)) return false;
        if (!Objects.equals(city, address.city)) return false;
        if (!Objects.equals(state, address.state)) return false;
        return Objects.equals(zip, address.zip);
    }
}
