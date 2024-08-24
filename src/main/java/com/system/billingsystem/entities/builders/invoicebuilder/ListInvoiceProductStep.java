package com.system.billingsystem.entities.builders.invoicebuilder;

import com.system.billingsystem.entities.InvoiceProduct;

import java.util.List;

public interface ListInvoiceProductStep {
    InvoiceBuildStep ListInvoiceProducts(List<InvoiceProduct> invoiceProducts);
}
