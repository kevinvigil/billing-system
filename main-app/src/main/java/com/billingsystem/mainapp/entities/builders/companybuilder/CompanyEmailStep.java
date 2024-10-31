package com.billingsystem.mainapp.entities.builders.companybuilder;

import com.billingsystem.mainapp.entities.microtypes.Mail;

public interface CompanyEmailStep {
    CompanySoldInvoicesStep email(Mail email);
}
