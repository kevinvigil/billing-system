package com.system.billingsystem.entities.builders.invoiceproductbuilder;

import com.system.billingsystem.entities.Invoice;
import com.system.billingsystem.entities.InvoiceProduct;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.entities.microtypes.ids.InvoiceProductId;

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
