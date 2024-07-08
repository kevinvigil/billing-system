package com.system.billingSystem.model;

import com.system.billingSystem.dto.InvoiceProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name = "InvoiceProduct")
public class InvoiceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private double amount;

    public InvoiceProduct() {}

    public InvoiceProduct(InvoiceProductDto in) {
        this.id = in.id();
        this.invoice = new Invoice(in.idInvoice());
        this.product = new Product(in.idProduct());
        this.amount = in.amount();
    }


    @Override
    public String toString(){
        return ("ID: " + this.id + " invoice_id: " + this.invoice.getId());
    }
}
