package com.system.billingsystem.entities.builders.invoicebuilder;

import com.system.billingsystem.entities.microtypes.Discount;

public interface InvoiceDiscountStep {
    InvoiceVoucherStep discount(Discount discount);
}
