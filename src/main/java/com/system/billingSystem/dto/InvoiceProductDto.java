package com.system.billingSystem.dto;

import com.system.billingSystem.model.InvoiceProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvoiceProductDto {
    private Long id;
    private String name;
    private double amount;
    private Long idProduct;
    private Long idInvoice;

    public InvoiceProductDto(InvoiceProduct invoiceProduct){
        this.id = invoiceProduct.getId();
        this.amount = invoiceProduct.getAmount();
        this.name = invoiceProduct.getProduct().getName();
        this.idInvoice = invoiceProduct.getInvoice().getId();
        this.idProduct = invoiceProduct.getProduct().getId();
    }
}
