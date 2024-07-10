package com.system.billingSystem.model;

import com.system.billingSystem.dto.InvoiceDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.OffsetDateTime;
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
    private Company company;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceProduct> products;

    public static Invoice newInvoice(InvoiceDto dto){
        Invoice invoice = new Invoice();
        invoice.setId(dto.id());
        invoice.setDate(dto.date());
        invoice.setPaid(dto.paid());
        invoice.setInvoiced(dto.invoiced());
        invoice.setTotal(dto.total());
        invoice.setInvoiceVoucher(InvoiceVoucher.valueOf(dto.invoiceVoucher()));
        invoice.setType(InvoiceType.valueOf(dto.type()));
        invoice.setCompany(new Company(dto.company()));
        invoice.setCustomer(new Customer(dto.customer()));
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
                ", company=" + ((company != null) ? company.getId() : "null") +
                ", customer=" + ((customer != null) ? customer.getId() : "null") +
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

        if (this.company != null && invoice.company != null)
            if (!Objects.equals(company, invoice.company))
                return false;
            else ;
        else if  (this.company != null || invoice.company != null)
            return false;

        if (this.customer != null && invoice.customer != null)
            if (!Objects.equals(customer, invoice.customer))
                return false;
            else ;
        else if (this.customer != null || invoice.customer != null)
            return false;

        return Objects.equals(customer, invoice.customer);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
