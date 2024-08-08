package com.system.billingSystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class InvoiceProduct {
    private UUID invoiceproduct_id;

    private Invoice invoice;

    private Product product;

    private Integer amount;

    public InvoiceProduct() {}

    @Override
    public String toString(){
        return ("InvoiceProduct { " +
                ", invoiceProduct_id: " + this.invoiceproduct_id +
                ", invoice_id: " + ((this.invoice == null)? null:this.invoice.getInvoice_id()) +
                ", product_id: " + ((this.product == null)? null: this.product.getProduct_id()) +
                ", amount: " + this.amount +
                " }"
        );
    }
}
