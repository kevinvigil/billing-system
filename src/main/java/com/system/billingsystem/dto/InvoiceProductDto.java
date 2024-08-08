package com.system.billingsystem.dto;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public record InvoiceProductDto (
        UUID invoiceProductDto_id,
        String name,
        Integer amount,
        UUID idProduct,
        UUID idInvoice ) {

    public InvoiceProductDto(UUID invoiceProductDto_id, String name, Integer amount, UUID idProduct, UUID idInvoice) {
        this.invoiceProductDto_id = invoiceProductDto_id;
        this.name = name;
        this.amount = amount;
        this.idProduct = idProduct;
        this.idInvoice = idInvoice;
    }

    public static boolean compareProducts(InvoiceProductDto product1, InvoiceProductDto product2) {
        if (product1 == product2) {
            return true;
        }
        if (product1 == null || product2 == null) {
            return false;
        }

        return Objects.equals(product1.invoiceProductDto_id(), product2.invoiceProductDto_id())
                && Objects.equals(product1.name(), product2.name())
                && product1.amount().compareTo(product2.amount()) == 0
                && Objects.equals(product1.idProduct(), product2.idProduct())
                && Objects.equals(product1.idInvoice(), product2.idInvoice());
    }


    @Override
    public UUID invoiceProductDto_id() {
        return invoiceProductDto_id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Integer amount() {
        return amount;
    }

    @Override
    public UUID idProduct() {
        return idProduct;
    }

    @Override
    public UUID idInvoice() {
        return idInvoice;
    }
}
