package com.system.billingsystem.entities.builders.productbuilder;

import com.system.billingsystem.entities.microtypes.prices.ProductPrice;

public interface ProductPriceStep {
    ProductCountStep price(ProductPrice price);
}
