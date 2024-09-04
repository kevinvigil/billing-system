package com.system.billingsystem.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public record InvoiceProductDto (
        UUID invoiceProductId,
        UUID productId,
        Integer count,
        String name,
        String description,
        BigDecimal price) {

    public InvoiceProductDto(UUID invoiceProductId, UUID productId, Integer count,
                             String name, String description, BigDecimal price) {
        this.invoiceProductId = invoiceProductId;
        this.count = count;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public static boolean compareProducts(InvoiceProductDto product1, InvoiceProductDto product2) {
        if (product1 == product2) {
            return true;
        }
        if (product1 == null || product2 == null) {
            return false;
        }

        return product1.price.compareTo(product2.price) == 0
                && Objects.equals(product1.count, product2.count)
                && Objects.equals(product1.name, product2.name)
                && Objects.equals(product1.description, product2.description);
    }

    public Integer count() {
        return count;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public BigDecimal price() {
        return price;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public UUID productId() {
        return productId;
    }

    @Override
    public UUID invoiceProductId() {
        return invoiceProductId;
    }
}
