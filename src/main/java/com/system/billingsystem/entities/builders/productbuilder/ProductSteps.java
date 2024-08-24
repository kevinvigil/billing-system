package com.system.billingsystem.entities.builders.productbuilder;

import com.system.billingsystem.entities.Product;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.entities.microtypes.names.ProductName;
import com.system.billingsystem.entities.microtypes.prices.ProductPrice;

public class ProductSteps implements ProductBuildStep, ProductDescriptionStep, ProductIdStep, ProductNameStep, ProductPriceStep {
    private ProductId productId;
    private ProductName name;
    private String description;
    private ProductPrice price;


    @Override
    public Product build() {
        return new Product(productId, name, description, price);
    }

    @Override
    public ProductPriceStep description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public ProductNameStep productId(ProductId productId) {
        this.productId = productId;
        return this;
    }

    @Override
    public ProductDescriptionStep name(ProductName name) {
        this.name = name;
        return this;
    }

    @Override
    public ProductBuildStep price(ProductPrice price) {
        this.price = price;
        return this;
    }
}