package com.system.billingsystem.entities.microtypes;

import lombok.Data;

import java.util.Objects;

@Data
public class Phone {
    private String countryCode;
    private String zoneCode;
    private String phoneNumber;

    public Phone(String countryCode, String zoneCode, String phoneNumber) {
        this.countryCode = countryCode;
        this.zoneCode = zoneCode;
        this.phoneNumber = phoneNumber;
    }

    public Phone(String zoneCode, String phoneNumber) {
        this.zoneCode = zoneCode;
        this.phoneNumber = phoneNumber;
    }

    public Phone(){}

    @Override
    public String toString() {
        return countryCode +" " + zoneCode +" " + phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        if (!Objects.equals(countryCode, phone.countryCode)) return false;
        if (!Objects.equals(zoneCode, phone.countryCode)) return false;
        return Objects.equals(phoneNumber, phone.phoneNumber);
    }
}
