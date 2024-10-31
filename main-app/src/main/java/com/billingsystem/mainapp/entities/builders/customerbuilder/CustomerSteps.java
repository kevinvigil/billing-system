package com.billingsystem.mainapp.entities.builders.customerbuilder;

import com.billingsystem.mainapp.entities.Company;
import com.billingsystem.mainapp.entities.Customer;
import com.billingsystem.mainapp.entities.microtypes.Mail;
import com.billingsystem.mainapp.entities.microtypes.ids.CustomerId;
import com.billingsystem.mainapp.entities.microtypes.names.CustomerName;

public class CustomerSteps implements CustomerIdStep, CustomerEmailStep, CustomerNameStep, CustomerPasswordStep,
        CustomerCompanyStep, CustomerBuildStep {

    private CustomerId customerId;
    private CustomerName name;
    private String password;
    private Mail email;

    private Company company;

    @Override
    public Customer build() {
        return new Customer(customerId, name, password, email, company);
    }

    @Override
    public CustomerNameStep CustomerId(CustomerId customerId) {
        this.customerId = customerId;
        return this;
    }

    @Override
    public CustomerPasswordStep name(CustomerName name) {
        this.name = name;
        return this;
    }

    @Override
    public CustomerEmailStep password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public CustomerCompanyStep email(Mail email) {
        this.email = email;
        return this;
    }

    @Override
    public CustomerBuildStep company(Company company) {
        this.company = company;
        return this;
    }
}
