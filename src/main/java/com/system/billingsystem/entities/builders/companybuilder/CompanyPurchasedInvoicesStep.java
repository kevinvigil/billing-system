package com.system.billingsystem.entities.builders.companybuilder;

import com.system.billingsystem.entities.Invoice;

import java.util.List;

public interface CompanyPurchasedInvoicesStep {
    CompanyBuildStep purchasedInvoices(List<Invoice> invoices);
}
