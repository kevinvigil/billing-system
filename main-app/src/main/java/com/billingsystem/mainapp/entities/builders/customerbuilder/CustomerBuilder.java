package com.billingsystem.mainapp.entities.builders.customerbuilder;

public class CustomerBuilder {

    public static CustomerIdStep newBuilder() {
        return new CustomerSteps();
    }
}
