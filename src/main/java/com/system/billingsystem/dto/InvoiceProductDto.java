package com.system.billingsystem.dto;

import java.util.Objects;
import java.util.UUID;

public record InvoiceProductDto (
        UUID invoiceProductId,
        String name,
        Integer amount,
        UUID productId,
        UUID invoiceId) {

    public InvoiceProductDto(UUID invoiceProductId, String name, Integer amount, UUID productId, UUID invoiceId) {
        this.invoiceProductId = invoiceProductId;
        this.name = name;
        this.amount = amount;
        this.productId = productId;
        this.invoiceId = invoiceId;
    }

    public static boolean compareProducts(InvoiceProductDto product1, InvoiceProductDto product2) {
        if (product1 == product2) {
            return true;
        }
        if (product1 == null || product2 == null) {
            return false;
        }

        return Objects.equals(product1.invoiceProductId(), product2.invoiceProductId())
                && Objects.equals(product1.name(), product2.name())
                && product1.amount().compareTo(product2.amount()) == 0
                && Objects.equals(product1.productId(), product2.productId())
                && Objects.equals(product1.invoiceId(), product2.invoiceId());
    }


    public UUID invoiceProductId() {
        return invoiceProductId;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Integer amount() {
        return amount;
    }

    public UUID productId() {
        return productId;
    }

    public UUID invoiceId() {
        return invoiceId;
    }
}
