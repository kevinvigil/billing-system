package com.billingsystem.authservice.entity.builder.customerbuilder;

import com.billingsystem.authservice.entity.Customer;
import com.billingsystem.authservice.entity.microtypes.Id;
import com.billingsystem.authservice.entity.microtypes.Mail;
import com.billingsystem.authservice.entity.microtypes.Name;

public class CustomerSteps implements CustomerIdStep, CustomerEmailStep, CustomerNameStep, CustomerPasswordStep,
         CustomerBuildStep {

    private Id customerId;
    private Name name;
    private String password;
    private Mail email;

    @Override
    public Customer build() {
        return new Customer(customerId, name, password, email);
    }

    @Override
    public CustomerNameStep CustomerId(Id customerId) {
        this.customerId = customerId;
        return this;
    }

    @Override
    public CustomerPasswordStep name(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public CustomerEmailStep password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public CustomerBuildStep email(Mail email) {
        this.email = email;
        return this;
    }

}
