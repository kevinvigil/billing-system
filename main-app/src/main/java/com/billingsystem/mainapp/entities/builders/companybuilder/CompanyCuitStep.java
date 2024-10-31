package com.billingsystem.mainapp.entities.builders.companybuilder;

import com.billingsystem.mainapp.entities.microtypes.Cuit;

public interface CompanyCuitStep {
    CompanyAddressStep cuit(Cuit cuit);
}
