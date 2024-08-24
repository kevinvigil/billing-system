package com.system.billingsystem.entities.microtypes;

import lombok.Data;

@Data
public class Cuit {

    private String cuit;

    public Cuit(String cuit) {
        this.cuit = cuit;
    }

    public Cuit() {}
}
