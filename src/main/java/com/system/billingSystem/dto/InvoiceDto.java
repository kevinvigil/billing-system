package com.system.billingSystem.dto;

import com.system.billingSystem.model.Invoice;
import com.system.billingSystem.model.InvoiceProduct;
import org.jetbrains.annotations.NotNull;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record InvoiceDto (
        Long id,
        OffsetDateTime date,
        boolean paid,
        boolean invoiced,
        double total,
        String invoiceVoucher,
        String type,
        Long company,
        Long customer,
        List<InvoiceProductDto> products ) {

    public InvoiceDto(Long id, OffsetDateTime date, boolean paid, boolean invoiced, double total,
                      String invoiceVoucher, String type, Long company, Long customer,
                      List<InvoiceProductDto> products) {
        this.id = id;
        this.date = date;
        this.paid = paid;
        this.invoiced = invoiced;
        this.total = total;
        this.invoiceVoucher = invoiceVoucher;
        this.type = type;
        this.company = company;
        this.customer = customer;
        this.products = products;
    }

    public static InvoiceDto newInvoiceDto (@NotNull Invoice i){
        List<InvoiceProduct> productList = i.getProducts();
        List<InvoiceProductDto> products = new ArrayList<InvoiceProductDto>();
        if (productList != null && !productList.isEmpty()){
            for (InvoiceProduct IP:productList) {
                products.add(InvoiceProductDto.newInvoiceProductDto(IP));
            }
        }
        return new InvoiceDto(i.getId(), i.getDate(), i.isPaid(), i.isInvoiced(),
                i.getTotal(), i.getInvoiceVoucher().name(), i.getType().name(), i.getCompany().getId(),
                i.getCustomer().getId(), products);
    }

    @Override
    public String toString() {
        return "YourClassName{" +
                "id=" + id +
                ", date=" + date +
                ", paid=" + paid +
                ", invoiced=" + invoiced +
                ", total=" + total +
                ", invoiceVoucher='" + invoiceVoucher + '\'' +
                ", type='" + type + '\'' +
                ", company=" + company +
                ", customer=" + customer +
                '}';
    }

    public static boolean compareInvoices(InvoiceDto invoice1, InvoiceDto invoice2) {
        if (invoice1 == invoice2) {
            return true;
        }
        if (invoice1 == null || invoice2 == null) {
            return false;
        }

        return Objects.equals(invoice1.id(), invoice2.id())
                && Objects.equals(invoice1.date(), invoice2.date())
                && invoice1.paid() == invoice2.paid()
                && Double.compare(invoice1.total(), invoice2.total()) == 0
                && Objects.equals(invoice1.invoiceVoucher(), invoice2.invoiceVoucher())
                && Objects.equals(invoice1.type(), invoice2.type())
                && Objects.equals(invoice1.company(), invoice2.company())
                && Objects.equals(invoice1.customer(), invoice2.customer())
                && compareProductLists(invoice1.products(), invoice2.products());
    }

    private static boolean compareProductLists(List<InvoiceProductDto> products1, List<InvoiceProductDto> products2) {
        if (products1 == products2) {
            return true;
        }
        if (products1 == null || products2 == null || products1.size() != products2.size()) {
            return false;
        }

        for (int i = 0; i < products1.size(); i++) {
            if (!InvoiceProductDto.compareProducts(products1.get(i), products2.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public OffsetDateTime date() {
        return date;
    }

    @Override
    public boolean paid() {
        return paid;
    }

    @Override
    public boolean invoiced() {
        return invoiced;
    }

    @Override
    public double total() {
        return total;
    }

    public String invoiceVoucher() {
        return invoiceVoucher;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public Long company() {
        return company;
    }

    @Override
    public Long customer() {
        return customer;
    }

    @Override
    public List<InvoiceProductDto> products() {
        return products;
    }
}
