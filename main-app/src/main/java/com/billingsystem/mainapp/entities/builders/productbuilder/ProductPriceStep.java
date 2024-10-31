package com.billingsystem.mainapp.entities.builders.productbuilder;

import com.billingsystem.mainapp.entities.microtypes.prices.ProductPrice;

public interface ProductPriceStep {
    ProductCountStep price(ProductPrice price);
}
