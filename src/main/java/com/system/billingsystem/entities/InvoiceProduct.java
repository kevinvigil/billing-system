package com.system.billingsystem.entities;

import com.system.billingsystem.entities.microtypes.ids.InvoiceProductId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class InvoiceProduct {
    private Invoice invoice;

    private Product product;

    private Integer count;

    public InvoiceProduct(Integer count, Product product) {
        this.count = count;
        this.product = product;
    }

    public InvoiceProduct() {}

    @Override
    public String toString(){
        return ("InvoiceProduct { " +
                ", invoice_id: " + ((this.invoice == null)? null:this.invoice.getInvoiceId().getValue()) +
                ", product_id: " + ((this.product == null)? null: this.product.getProductId().getValue()) +
                ", count: " + this.count +
                " }"
        );
    }
}
