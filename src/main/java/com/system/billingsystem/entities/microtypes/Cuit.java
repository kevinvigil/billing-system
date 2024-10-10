package com.system.billingsystem.entities.microtypes;

import lombok.Data;

@Data
public class Cuit {

    private String cuit;

    public Cuit(String cuit) {
        this.cuit = cuit;
    }

    public Cuit() {}

    @Override
    public String toString() {
        return cuit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuit cuit = (Cuit) o;
        return cuit.equals(cuit.cuit);
    }
}
