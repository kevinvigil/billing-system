package com.system.billingsystem.entities.builders.customerbuilder;

import com.system.billingsystem.entities.Company;
import com.system.billingsystem.entities.Customer;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.entities.microtypes.names.CustomerName;

public class CustomerSteps implements CustomerIdStep, CustomerEmailStep, CustomerNameStep, CustomerPasswordStep, CustomerCompanyStep, CustomerBuildStep {

    private CustomerId customerId;
    private CustomerName name;
    private String password;
    private String email;

    private Company company = null;

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
    public CustomerCompanyStep email(String email) {
        this.email = email;
        return this;
    }

    @Override
    public CustomerBuildStep company(Company company) {
        this.company = company;
        return this;
    }
}
