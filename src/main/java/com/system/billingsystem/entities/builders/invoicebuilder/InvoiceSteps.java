package com.system.billingsystem.entities.builders.invoicebuilder;

import com.system.billingsystem.entities.*;
import com.system.billingsystem.entities.microtypes.Discount;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.entities.Currency;
import com.system.billingsystem.entities.microtypes.prices.InvoicePrice;

import java.sql.Timestamp;
import java.util.List;

public class InvoiceSteps implements InvoiceBuildStep, InvoiceBuyerCompanyStep, InvoiceCategoryStep,
        InvoiceDateStep, InvoiceDiscountStep, InvoiceIdStep, InvoiceInvoicedStep, InvoicePaidStep, 
        InvoicePriceStep, InvoiceSellerCompanyStep, InvoiceVoucherStep, ListInvoiceProductStep {

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


    @Override
    public Invoice build() {
        return new Invoice(invoiceId, date, paid, invoiced, price, discount, invoicevoucher, category, sellerCompany, buyerCompany, products);
    }

    @Override
    public ListInvoiceProductStep buyerCompany(Company company) {
        this.buyerCompany = company;
        return this;
    }

    @Override
    public InvoiceSellerCompanyStep category(InvoiceCategory category) {
        this.category = category;
        return this;
    }

    @Override
    public InvoicePaidStep date(Timestamp date) {
        this.date = date;
        return this;
    }

    @Override
    public InvoiceVoucherStep discount(Discount discount) {
        this.discount = discount;
        return this;
    }

    @Override
    public InvoiceDateStep invoiceId(InvoiceId invoiceId) {
        this.invoiceId = invoiceId;
        return this;
    }

    @Override
    public InvoicePriceStep invoiced(boolean invoiced) {
        this.invoiced = invoiced;
        return this;
    }

    @Override
    public InvoiceInvoicedStep paid(boolean paid) {
        this.paid = paid;
        return this;
    }

    @Override
    public InvoiceDiscountStep price(InvoicePrice price) {
        this.price = price;
        return this;
    }

    @Override
    public InvoiceBuyerCompanyStep sellerCompany(Company company) {
        this.sellerCompany = company;
        return this;
    }

    @Override
    public InvoiceCategoryStep invoiceVoucher(InvoiceVoucher voucher) {
        this.invoicevoucher = voucher;
        return this;
    }

    @Override
    public InvoiceBuildStep ListInvoiceProducts(List<InvoiceProduct> invoiceProducts) {
        this.products = invoiceProducts;
        return this;
    }
}
