package com.system.billingsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class InvoiceProduct {
    private UUID invoiceProduct_id;

    private Invoice invoice;

    private Product product;

    private BigDecimal amount;

    public InvoiceProduct() {}

    @Override
    public String toString(){
        return ("InvoiceProduct { " +
                ", invoiceProduct_id: " + this.invoiceProduct_id +
                ", invoice_id: " + this.invoice.getInvoice_id() +
                ", product_id: " + this.product.getProduct_id() +
                ", amount: " + this.amount +
                " }"
        );
    }
}
