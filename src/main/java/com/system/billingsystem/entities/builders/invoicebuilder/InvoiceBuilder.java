package com.system.billingsystem.entities.builders.invoicebuilder;

public class InvoiceBuilder {
    public static InvoiceIdStep newBuilder(){
        return new InvoiceSteps();
    }
}
