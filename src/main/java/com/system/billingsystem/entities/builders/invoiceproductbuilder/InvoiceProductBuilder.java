package com.system.billingsystem.entities.builders.invoiceproductbuilder;

public class InvoiceProductBuilder {
    public static InvoiceProductIdStep newBuilder(){
        return new InvoiceProductSteps();
    }
}
