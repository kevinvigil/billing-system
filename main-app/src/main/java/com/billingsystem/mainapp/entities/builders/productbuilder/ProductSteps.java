package com.billingsystem.mainapp.entities.builders.productbuilder;

import com.billingsystem.mainapp.entities.Product;
import com.billingsystem.mainapp.entities.microtypes.ids.ProductId;
import com.billingsystem.mainapp.entities.microtypes.names.ProductName;
import com.billingsystem.mainapp.entities.microtypes.prices.ProductPrice;

public class ProductSteps implements ProductBuildStep, ProductDescriptionStep, ProductIdStep, ProductNameStep,
        ProductPriceStep, ProductCountStep {
    private ProductId productId;
    private ProductName name;
    private String description;
    private ProductPrice price;
    private Integer count;


    @Override
    public Product build() {
        return new Product(productId, name, description, price, count);
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
    public ProductCountStep price(ProductPrice price) {
        this.price = price;
        return this;
    }

    @Override
    public ProductBuildStep count(Integer count) {
        this.count = count;
        return this;
    }
}
