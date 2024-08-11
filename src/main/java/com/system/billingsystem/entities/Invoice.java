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

    private UUID invoiceId;

    private Timestamp date;
    private boolean paid;
    private boolean invoiced;
    private BigDecimal total;

    @Max(100)
    @Min(0)
    private Integer discount;

    private InvoiceVoucher invoicevoucher;

    private InvoiceCategory category;

    private Company sellerCompany;

    public Invoice(UUID invoiceId, Timestamp date, boolean paid, boolean invoiced, BigDecimal total,
                   Integer discount, InvoiceVoucher invoicevoucher, InvoiceCategory category,
                   Company sellerCompany, Company buyerCompany) {
        this.invoiceId = invoiceId;
        this.date = date;
        this.paid = paid;
        this.invoiced = invoiced;
        this.total = total;
        this.discount = discount;
        this.invoicevoucher = invoicevoucher;
        this.category = category;
        this.sellerCompany = sellerCompany;
        this.buyerCompany = buyerCompany;
    }

    private Company buyerCompany;

    private List<InvoiceProduct> products;


    public Invoice() {}

    public Invoice(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void addProduct(InvoiceProduct invoiceProduct){
        this.products.add(invoiceProduct);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoiceId +
                ", date=" + date +
                ", paid=" + paid +
                ", invoiced=" + invoiced +
                ", total=" + total +
                ", invoice voucher='" + ((invoicevoucher == null)? null: invoicevoucher.name()) + '\'' +
                ", type='" + category.name() + '\'' +
                ", company=" + ((sellerCompany != null) ? sellerCompany.getCompanyId() : "null") +
                ", customer=" + ((buyerCompany != null) ? buyerCompany.getCompanyId() : "null") +
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
                        (!Objects.equals(invoiceId, invoice.invoiceId)) ||
                        (!Objects.equals(date, invoice.date)) ||
                        (invoicevoucher != invoice.invoicevoucher) ||
                        (category != invoice.category))return false;

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
