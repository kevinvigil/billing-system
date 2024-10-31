package com.billingsystem.mainapp.entities.builders.invoiceproductbuilder;

import com.billingsystem.mainapp.entities.Invoice;
import com.billingsystem.mainapp.entities.InvoiceProduct;
import com.billingsystem.mainapp.entities.Product;

public class InvoiceProductSteps implements InvoiceProductBuildStep, InvoiceProductCountStep, InvoiceProductInvoiceStep, InvoiceProductProductStep{

    private Invoice invoice;
    private Product product;
    private Integer count;

    @Override
    public InvoiceProduct build() {
        return new InvoiceProduct(invoice, product, count);
    }

    @Override
    public InvoiceProductInvoiceStep count(Integer count) {
        this.count = count;
        return this;
    }

    @Override
    public InvoiceProductProductStep invoice(Invoice invoice) {
        this.invoice = invoice;
        return this;
    }

    @Override
    public InvoiceProductBuildStep product(Product product) {
        this.product = product;
        return this;
    }
}
