package com.billingsystem.mainapp.entities.builders.customerbuilder;

import com.billingsystem.mainapp.entities.microtypes.ids.CustomerId;

public interface CustomerIdStep {
    CustomerNameStep CustomerId(CustomerId customerId);
}
