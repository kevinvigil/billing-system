package com.system.billingsystem.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Invoice {

    private UUID invoice_id;

    private Timestamp date;
    private boolean paid;
    private boolean invoiced;
    private BigDecimal total;

    @Max(100)
    @Min(0)
    private Integer discount;

    private InvoiceVoucher invoicevoucher;

    private InvoiceType type;

    private Company sellerCompany;

    private Company buyerCompany;

    private List<InvoiceProduct> products;


    public Invoice() {}

    public Invoice(UUID invoice_id) {
        this.invoice_id = invoice_id;
    }

    public void addProduct(InvoiceProduct invoiceProduct){
        this.products.add(invoiceProduct);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", date=" + date +
                ", paid=" + paid +
                ", invoiced=" + invoiced +
                ", total=" + total +
                ", invoicevoucher='" + ((invoicevoucher == null)? null: invoicevoucher.name()) + '\'' +
                ", type='" + type.name() + '\'' +
                ", company=" + ((sellerCompany != null) ? sellerCompany.getCompany_id() : "null") +
                ", customer=" + ((buyerCompany != null) ? buyerCompany.getCompany_id() : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (
                (invoice.total.compareTo(total) != 0) ||
                        (paid != invoice.paid) ||
                        (invoiced != invoice.invoiced) ||
                        (!Objects.equals(invoice_id, invoice.invoice_id)) ||
                        (!Objects.equals(date, invoice.date)) ||
                        (invoicevoucher != invoice.invoicevoucher) ||
                        (type != invoice.type))return false;

        if (this.sellerCompany != null && invoice.sellerCompany != null){
            if (!Objects.equals(sellerCompany, invoice.sellerCompany)){
                return false;
            }
        }else if  (invoice.sellerCompany != null || this.sellerCompany != null)
            return false;

        if (this.buyerCompany != null && invoice.buyerCompany != null){
            if (!Objects.equals(buyerCompany, invoice.buyerCompany)){
                return false;
            }
        }else if (this.buyerCompany != null || invoice.buyerCompany != null)
            return false;

        return Objects.equals(buyerCompany, invoice.buyerCompany);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public List<InvoiceProduct> getProducts() {
        return new ArrayList<>((this.products == null)? new ArrayList<>() : this.products);
    }
}
