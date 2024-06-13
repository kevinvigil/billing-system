package com.system.billingSystem.model;

import com.system.billingSystem.dto.InvoiceDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Invoice")
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OffsetDateTime date;
    private boolean paid;
    private boolean invoiced;
    private double total;

    @Enumerated(EnumType.STRING)
    private InvoiceVoucher invoiceVoucher;

    @Enumerated(EnumType.STRING)
    private InvoiceType type;

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
        return "YourClassName{" +
                "id=" + id +
                ", date=" + date +
                ", paid=" + paid +
                ", invoiced=" + invoiced +
                ", total=" + total +
                ", invoiceVoucher='" + invoiceVoucher.name() + '\'' +
                ", type='" + type.name() + '\'' +
                ", company=" + company.getId() +
                ", customer=" + customer.getId() +
                '}';
    }
}
