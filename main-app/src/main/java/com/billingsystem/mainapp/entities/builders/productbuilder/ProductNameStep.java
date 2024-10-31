package com.billingsystem.mainapp.entities.builders.productbuilder;

import com.billingsystem.mainapp.entities.microtypes.names.ProductName;

public interface ProductNameStep {
    ProductDescriptionStep name(ProductName name);
}
