package com.billingsystem.authservice.entity.builder.customerbuilder;


import com.billingsystem.authservice.entity.microtypes.Mail;

public interface CustomerEmailStep {
    CustomerBuildStep email(Mail email);
}
