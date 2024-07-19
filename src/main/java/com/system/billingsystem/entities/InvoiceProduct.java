package com.system.billingsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class InvoiceProduct {
    private UUID id;

    private Invoice invoice;

    private Product product;

    private Double amount;

    public InvoiceProduct() {}

    @Override
    public String toString(){
        return ("InvoiceProduct { " +
                ", id: " + this.id +
                ", invoice_id: " + this.invoice.getId() +
                ", product_id: " + this.product.getId() +
                ", amount: " + this.amount +
                " }"
        );
    }
}
