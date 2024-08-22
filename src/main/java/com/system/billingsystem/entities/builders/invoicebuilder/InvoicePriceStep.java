package com.system.billingsystem.entities.builders.invoicebuilder;

import com.system.billingsystem.entities.microtypes.prices.InvoicePrice;

public interface InvoicePriceStep {
    InvoiceDiscountStep price(InvoicePrice price);
}
