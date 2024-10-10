package com.system.billingsystem.entities.builders.companybuilder;

import com.system.billingsystem.entities.microtypes.Mail;

public interface CompanyEmailStep {
    CompanySoldInvoicesStep email(Mail email);
}
