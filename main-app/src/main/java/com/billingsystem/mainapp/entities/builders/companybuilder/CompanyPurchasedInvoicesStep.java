package com.billingsystem.mainapp.entities.builders.companybuilder;

import com.billingsystem.mainapp.entities.Invoice;

import java.util.List;

public interface CompanyPurchasedInvoicesStep {
    CompanyBuildStep purchasedInvoices(List<Invoice> invoices);
}
