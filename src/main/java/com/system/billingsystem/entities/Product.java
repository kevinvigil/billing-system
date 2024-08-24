package com.system.billingsystem.entities;

import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.entities.microtypes.names.ProductName;
import com.system.billingsystem.entities.microtypes.prices.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {

    private ProductId productId;
    private ProductName name;
    private String description;
    private ProductPrice price;

    public Product() {}

    public Product(ProductId productId) {
        this.productId = productId;
    }

    @Override
    public String toString(){
        return ("Product { " +
                " product_id: " + this.productId +
                ", Name: " + this.name +
                ", Description: " + this.description +
                ", Price: " + this.price +
                " }");
    }
}

