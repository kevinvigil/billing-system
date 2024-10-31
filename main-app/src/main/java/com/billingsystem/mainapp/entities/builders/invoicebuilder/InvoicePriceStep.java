package com.billingsystem.mainapp.entities.builders.invoicebuilder;

import com.billingsystem.mainapp.entities.microtypes.prices.InvoicePrice;

public interface InvoicePriceStep {
    InvoiceDiscountStep price(InvoicePrice price);
}
