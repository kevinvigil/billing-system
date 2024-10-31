package com.billingsystem.mainapp.entities.builders.companybuilder;

import com.billingsystem.mainapp.entities.Invoice;

import java.util.List;

public interface CompanySoldInvoicesStep {
    CompanyPurchasedInvoicesStep soldInvoices(List<Invoice> invoices);
}
