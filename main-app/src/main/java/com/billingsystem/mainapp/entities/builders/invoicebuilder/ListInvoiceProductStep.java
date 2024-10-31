package com.billingsystem.mainapp.entities.builders.invoicebuilder;

import com.billingsystem.mainapp.entities.InvoiceProduct;

import java.util.List;

public interface ListInvoiceProductStep {
    InvoiceBuildStep ListInvoiceProducts(List<InvoiceProduct> invoiceProducts);
}
