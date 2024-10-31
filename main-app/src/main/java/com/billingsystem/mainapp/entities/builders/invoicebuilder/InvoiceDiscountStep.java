package com.billingsystem.mainapp.entities.builders.invoicebuilder;

import com.billingsystem.mainapp.entities.microtypes.Discount;

public interface InvoiceDiscountStep {
    InvoiceVoucherStep discount(Discount discount);
}
