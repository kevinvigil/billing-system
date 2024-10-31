package com.billingsystem.mainapp.entities.builders.customerbuilder;

import com.billingsystem.mainapp.entities.microtypes.names.CustomerName;

public interface CustomerNameStep {
    CustomerPasswordStep name(CustomerName name);
}
