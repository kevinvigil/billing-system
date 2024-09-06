package com.system.billingsystem.entities.microtypes;

import lombok.Data;

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
}
