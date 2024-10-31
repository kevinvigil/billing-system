package com.billingsystem.mainapp.entities.builders.invoicebuilder;

public class InvoiceBuilder {
    public static InvoiceIdStep newBuilder(){
        return new InvoiceSteps();
    }
}
