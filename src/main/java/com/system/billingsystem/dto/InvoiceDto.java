package com.system.billingsystem.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record InvoiceDto (
        UUID invoiceId,
        Timestamp date,
        boolean paid,
        boolean invoiced,
        BigDecimal price,
        String currency,
        Integer discount,
        String invoiceVoucher,
        String category,
        CompanyDto sellerCompany,
        CompanyDto buyerCompany,
        List<InvoiceProductDto> products ) {

    public InvoiceDto(UUID invoiceId, Timestamp date, boolean paid, boolean invoiced, BigDecimal price, String currency,
                      Integer discount, String invoiceVoucher, String category, CompanyDto sellerCompany, CompanyDto buyerCompany,
                      List<InvoiceProductDto> products) {
        this.invoiceId = invoiceId;
        this.date = date;
        this.paid = paid;
        this.invoiced = invoiced;
        this.price = price;
        this.discount = discount;
        this.currency = currency;
        this.invoiceVoucher = invoiceVoucher;
        this.category = category;
        this.sellerCompany = sellerCompany;
        this.buyerCompany = buyerCompany;
        this.products = products;
    }

    public static boolean compareInvoices(InvoiceDto invoice1, InvoiceDto invoice2) {
        if (invoice1 == invoice2) {
            return true;
        }
        if (invoice1 == null || invoice2 == null) {
            return false;
        }

        return Objects.equals(invoice1.invoiceId(), invoice2.invoiceId())
                && Objects.equals(invoice1.date(), invoice2.date())
                && invoice1.paid() == invoice2.paid()
                && invoice1.price().compareTo(invoice2.price()) == 0
                && Objects.equals(invoice1.invoiceVoucher(), invoice2.invoiceVoucher())
                && Objects.equals(invoice1.category(), invoice2.category())
                && Objects.equals(invoice1.sellerCompany(), invoice2.sellerCompany())
                && Objects.equals(invoice1.buyerCompany(), invoice2.buyerCompany())
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

    public UUID invoiceId() {
        return invoiceId;
    }

    @Override
    public Timestamp date() {
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

    public BigDecimal price() {
        return price;
    }

    public String invoiceVoucher() {
        return invoiceVoucher;
    }

    @Override
    public String category() {
        return category;
    }

    @Override
    public CompanyDto sellerCompany() {
        return sellerCompany;
    }

    @Override
    public CompanyDto buyerCompany() {
        return buyerCompany;
    }

    @Override
    public List<InvoiceProductDto> products() {
        return products;
    }

    @Override
    public boolean equals(Object obj){
        if (obj instanceof InvoiceDto dto) {
            return Objects.equals(this.invoiceId, dto.invoiceId())
                    && Objects.equals(this.date, dto.date())
                    && this.paid == dto.paid()
                    && this.invoiced == dto.invoiced()
                    && this.price.compareTo(dto.price()) == 0
                    && this.currency.equals(dto.currency())
                    && Objects.equals(this.discount, dto.discount())
                    && Objects.equals(this.invoiceVoucher, dto.invoiceVoucher())
                    && Objects.equals(this.category, dto.category());
        }
        return false;
    }
}
