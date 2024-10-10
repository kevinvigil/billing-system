package com.system.billingsystem.entities.builders.invoiceproductbuilder;

public class InvoiceProductBuilder {
    public static InvoiceProductCountStep newBuilder(){
        return new InvoiceProductSteps();
    }
}
