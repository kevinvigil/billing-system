package com.billingsystem.authservice.entity.builder.customerbuilder;

public class CustomerBuilder {

    public static CustomerIdStep newBuilder() {
        return new CustomerSteps();
    }
}
