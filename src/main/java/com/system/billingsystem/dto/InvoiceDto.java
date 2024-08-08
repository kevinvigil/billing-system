package com.system.billingsystem.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record InvoiceDto (
        UUID invoiceDtoId,
        Timestamp date,
        boolean paid,
        boolean invoiced,
        BigDecimal total,
        String invoiceVoucher,
        String type,
        UUID sellerCompany,
        UUID buyerCompany,
        List<InvoiceProductDto> products ) {

    public InvoiceDto(UUID invoiceDtoId, Timestamp date, boolean paid, boolean invoiced, BigDecimal total,
                      String invoiceVoucher, String type, UUID sellerCompany, UUID buyerCompany,
                      List<InvoiceProductDto> products) {
        this.invoiceDtoId = invoiceDtoId;
        this.date = date;
        this.paid = paid;
        this.invoiced = invoiced;
        this.total = total;
        this.invoiceVoucher = invoiceVoucher;
        this.type = type;
        this.sellerCompany = sellerCompany;
        this.buyerCompany = buyerCompany;
        this.products = products;
    }

    @Override
    public String toString() {
        return "InvoiceDto{" +
                "invoiceDtoId=" + invoiceDtoId +
                ", date=" + date +
                ", paid=" + paid +
                ", invoiced=" + invoiced +
                ", total=" + total +
                ", invoicevoucher='" + invoiceVoucher + '\'' +
                ", type='" + type + '\'' +
                ", company=" + sellerCompany +
                ", customer=" + buyerCompany +
                '}';
    }

    public static boolean compareInvoices(InvoiceDto invoice1, InvoiceDto invoice2) {
        if (invoice1 == invoice2) {
            return true;
        }
        if (invoice1 == null || invoice2 == null) {
            return false;
        }

        return Objects.equals(invoice1.invoiceDtoId(), invoice2.invoiceDtoId())
                && Objects.equals(invoice1.date(), invoice2.date())
                && invoice1.paid() == invoice2.paid()
                && invoice1.total().compareTo(invoice2.total()) == 0
                && Objects.equals(invoice1.invoiceVoucher(), invoice2.invoiceVoucher())
                && Objects.equals(invoice1.type(), invoice2.type())
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

    public UUID invoiceDtoId() {
        return invoiceDtoId;
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

    @Override
    public BigDecimal total() {
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
    public UUID sellerCompany() {
        return sellerCompany;
    }

    @Override
    public UUID buyerCompany() {
        return buyerCompany;
    }

    @Override
    public List<InvoiceProductDto> products() {
        return products;
    }
}
