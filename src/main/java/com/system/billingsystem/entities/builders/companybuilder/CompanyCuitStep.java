package com.system.billingsystem.entities.builders.companybuilder;

import com.system.billingsystem.entities.microtypes.Cuit;

public interface CompanyCuitStep {
    CompanyAddressStep cuit(Cuit cuit);
}
