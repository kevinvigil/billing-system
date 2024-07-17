package com.system.billingsystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static java.time.OffsetDateTime.now;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "Invoice")
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private OffsetDateTime date = now();
    private boolean paid = true;
    private boolean invoiced = false;
    private double total = 0;

    @Max(100)
    @Min(0)
    private Integer discount = 0;

    @Enumerated(EnumType.STRING)
    private InvoiceVoucher invoiceVoucher;

    @Enumerated(EnumType.STRING)
    private InvoiceType type = InvoiceType.B;

    @ManyToOne
    @JoinColumn(name = "seller_company_id", referencedColumnName = "company_id")
    private Company sellerCompany;

    @ManyToOne
    @JoinColumn(name = "buyer_company_id", referencedColumnName = "company_id")
    private Company buyerCompany;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceProduct> products = new ArrayList<InvoiceProduct>();


    public Invoice() {}

    public Invoice(UUID id) {
        this.id = id;
    }

    public void addProduct(InvoiceProduct invoiceProduct){
        this.products.add(invoiceProduct);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", date=" + date +
                ", paid=" + paid +
                ", invoiced=" + invoiced +
                ", total=" + total +
                ", invoiceVoucher='" + invoiceVoucher.name() + '\'' +
                ", type='" + type.name() + '\'' +
                ", company=" + ((sellerCompany != null) ? sellerCompany.getId() : "null") +
                ", customer=" + ((buyerCompany != null) ? buyerCompany.getId() : "null") +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        if ( (Double.compare(invoice.total, total) != 0) || (paid != invoice.paid) || (invoiced != invoice.invoiced)
        || (!Objects.equals(id, invoice.id)) || (!Objects.equals(date, invoice.date)) || (invoiceVoucher != invoice.invoiceVoucher)
        || (type != invoice.type))return false;

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
