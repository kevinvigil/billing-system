package com.system.billingsystem.entities.builders.productbuilder;

import com.system.billingsystem.entities.microtypes.prices.ProductPrice;

public interface ProductPriceStep {
    ProductBuildStep price(ProductPrice price);
}
