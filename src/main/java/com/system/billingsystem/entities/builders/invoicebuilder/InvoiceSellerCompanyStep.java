package com.system.billingsystem.entities.builders.invoicebuilder;

import com.system.billingsystem.entities.Company;

public interface InvoiceSellerCompanyStep {
    InvoiceBuyerCompanyStep sellerCompany(Company company);
}
