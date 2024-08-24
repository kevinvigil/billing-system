package com.system.billingsystem.dto;

import java.util.UUID;

public record InvoiceProductDto (
        UUID invoiceProductId,
        Integer count,
        ProductDto product) {

    public InvoiceProductDto(UUID invoiceProductId, Integer count, ProductDto product) {
        this.invoiceProductId = invoiceProductId;
        this.count = count;
        this.product = product;
    }

    public static boolean compareProducts(InvoiceProductDto product1, InvoiceProductDto product2) {
        if (product1 == product2) {
            return true;
        }
        if (product1 == null || product2 == null) {
            return false;
        }

        return product1.count().compareTo(product2.count()) == 0
                && product1.product.equals(product2.product);
    }

    public Integer count() {
        return count;
    }

    public ProductDto getProduct() {
        return product;
    }
}
