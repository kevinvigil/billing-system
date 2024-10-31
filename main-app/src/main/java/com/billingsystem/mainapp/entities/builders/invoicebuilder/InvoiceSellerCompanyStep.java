package com.billingsystem.mainapp.entities.builders.invoicebuilder;

import com.billingsystem.mainapp.entities.Company;

public interface InvoiceSellerCompanyStep {
    InvoiceBuyerCompanyStep sellerCompany(Company company);
}
