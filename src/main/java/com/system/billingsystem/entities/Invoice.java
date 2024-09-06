package com.system.billingsystem.entities;

import com.system.billingsystem.entities.microtypes.Discount;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.entities.microtypes.prices.InvoicePrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
public class Invoice {

    private InvoiceId invoiceId;
    private Timestamp date;
    private boolean paid;
    private boolean invoiced;
    private InvoicePrice price;
    private Discount discount;
    private InvoiceVoucher invoicevoucher;
    private InvoiceCategory category;
    private Company sellerCompany;
    private Company buyerCompany;
    private List<InvoiceProduct> products;

    public Invoice(InvoiceId invoiceId, Timestamp date, boolean paid, boolean invoiced, InvoicePrice price,
                   Discount discount, InvoiceVoucher invoicevoucher, InvoiceCategory category,
                   Company sellerCompany, Company buyerCompany) {
        this.invoiceId = invoiceId;
        this.date = date;
        this.paid = paid;
        this.invoiced = invoiced;
        this.price = price;
        this.discount = discount;
        this.invoicevoucher = invoicevoucher;
        this.category = category;
        this.sellerCompany = sellerCompany;
        this.buyerCompany = buyerCompany;
    }

    public Invoice() {}

    public Invoice(InvoiceId invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "\n invoice_id=" + invoiceId +
                ", \n date=" + date +
                ", \n paid=" + paid +
                ", \n invoiced=" + invoiced +
                ", \n total=" + price +
                ", \n invoice voucher='" + ((invoicevoucher == null)? null: invoicevoucher.name()) + '\'' +
                ", \n type='" + category.name() + '\'' +
                ", \n company=" + ((sellerCompany != null) ? sellerCompany.getCompanyId().getValue() : "null") +
                ", \n customer=" + ((buyerCompany != null) ? buyerCompany.getCompanyId().getValue() : "null") +
                " \n }";
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if (
                (invoice.price.compareTo(price) != 0) ||
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
