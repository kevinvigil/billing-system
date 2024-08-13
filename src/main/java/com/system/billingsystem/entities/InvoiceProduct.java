package com.system.billingsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class InvoiceProduct {
    private UUID invoiceproductId;

    private Invoice invoice;

    private Product product;

    private Integer amount;

    public InvoiceProduct() {}

    @Override
    public String toString(){
        return ("InvoiceProduct { " +
                ", invoiceProduct_id: " + this.invoiceproductId +
                ", invoice_id: " + ((this.invoice == null)? null:this.invoice.getInvoice_id()) +
                ", product_id: " + ((this.product == null)? null: this.product.getProduct_id()) +
                ", amount: " + this.amount +
                " }"
        );
    }
}
