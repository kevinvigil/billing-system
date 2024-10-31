package com.billingsystem.mainapp.entities.builders.customerbuilder;

import com.billingsystem.mainapp.entities.microtypes.Mail;

public interface CustomerEmailStep {
    CustomerCompanyStep email(Mail email);
}
