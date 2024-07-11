package com.system.billingsystem.DTOs;

import com.system.billingsystem.entities.InvoiceProduct;

import java.util.Objects;

public record InvoiceProductDto (
        Long id,
        String name,
        Double amount,
        Long idProduct,
        Long idInvoice ) {

    public InvoiceProductDto(Long id, String name, Double amount, Long idProduct, Long idInvoice) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.idProduct = idProduct;
        this.idInvoice = idInvoice;
    }

    public InvoiceProductDto (InvoiceProduct invoiceProduct){
        this (invoiceProduct.getId(), invoiceProduct.getProduct().getName(),
                invoiceProduct.getAmount(), invoiceProduct.getInvoice().getId(),
                invoiceProduct.getProduct().getId());
    }

    public static boolean compareProducts(InvoiceProductDto product1, InvoiceProductDto product2) {
        if (product1 == product2) {
            return true;
        }
        if (product1 == null || product2 == null) {
            return false;
        }

        return Objects.equals(product1.id(), product2.id())
                && Objects.equals(product1.name(), product2.name())
                && Double.compare(product1.amount(), product2.amount()) == 0
                && Objects.equals(product1.idProduct(), product2.idProduct())
                && Objects.equals(product1.idInvoice(), product2.idInvoice());
    }


    @Override
    public Long id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Double amount() {
        return amount;
    }

    @Override
    public Long idProduct() {
        return idProduct;
    }

    @Override
    public Long idInvoice() {
        return idInvoice;
    }
}
