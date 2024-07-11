package com.system.billingsystem.entities;

import com.system.billingsystem.DTOs.InvoiceDto;
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

import static java.time.OffsetDateTime.now;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "Invoice")
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private OffsetDateTime date = now();
    private boolean paid = true;
    private boolean invoiced = false;
    private double total = 0;

    @Max(100)
    @Min(0)
    private Integer discount = 0;

    @Enumerated(EnumType.STRING)
    private InvoiceVoucher invoiceVoucher = InvoiceVoucher.CASH;

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

    public static Invoice newInvoice(InvoiceDto dto){
        Invoice invoice = new Invoice();
        invoice.setId(dto.id());
        invoice.setPaid(dto.paid());
        invoice.setInvoiced(dto.invoiced());
        invoice.setTotal(dto.total());
        invoice.setInvoiceVoucher(InvoiceVoucher.valueOf(dto.invoiceVoucher()));
        invoice.setType(InvoiceType.valueOf(dto.type()));
        invoice.setSellerCompany(new Company(dto.sellerCompany()));
        invoice.setBuyerCompany(new Company(dto.buyerCompany()));
        return invoice;
    }

    public Invoice() {}

    public Invoice(Long id) {
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

        if (this.sellerCompany != null && invoice.sellerCompany != null)
            if (!Objects.equals(sellerCompany, invoice.sellerCompany))
                return false;
            else ;
        else if  (this.sellerCompany != null || invoice.sellerCompany != null)
            return false;

        if (this.buyerCompany != null && invoice.buyerCompany != null)
            if (!Objects.equals(buyerCompany, invoice.buyerCompany))
                return false;
            else ;
        else if (this.buyerCompany != null || invoice.buyerCompany != null)
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
