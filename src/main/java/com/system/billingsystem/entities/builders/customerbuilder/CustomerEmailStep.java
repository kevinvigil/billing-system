package com.system.billingsystem.entities.builders.customerbuilder;

import com.system.billingsystem.entities.microtypes.Mail;

public interface CustomerEmailStep {
    CustomerCompanyStep email(Mail email);
}
