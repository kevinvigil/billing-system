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
    private Integer count;

    public Product() {}

    public Product(ProductId productId) {
        this.productId = productId;
    }

    public Product(ProductId productId, ProductName productName, String description, ProductPrice productPrice) {
        this.productId = productId;
        this.name = productName;
        this.description = description;
        this.price = productPrice;
    }

    @Override
    public String toString(){
        return ("Product { " +
                " product_id: " + this.productId +
                ", Name: " + this.name +
                ", Description: " + this.description +
                ", Count: " + this.count +
                ", Price: " + this.price +
                " }");
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Product product) {
            return !(!this.productId.equals(product.productId) ||
                    !this.name.equals(product.name) ||
                    !this.description.equals(product.description) ||
                    !this.price.equals(product.price) ||
                    !this.count.equals(product.count)) ;
        }
        return false;
    }
}

