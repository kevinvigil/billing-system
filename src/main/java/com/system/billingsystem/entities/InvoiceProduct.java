package com.system.billingsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class InvoiceProduct {
    private UUID invoiceProductId;

    private Invoice invoice;

    private Product product;

    private Integer count;

    public InvoiceProduct() {}

    @Override
    public String toString(){
        return ("InvoiceProduct { " +
                ", invoiceProduct_id: " + this.invoiceProductId +
                ", invoice_id: " + ((this.invoice == null)? null:this.invoice.getInvoiceId()) +
                ", product_id: " + ((this.product == null)? null: this.product.getProductId()) +
                ", amount: " + this.count +
                " }"
        );
    }
}
