package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.InvoiceProductDto;
import com.system.billingsystem.entities.Invoice;
import com.system.billingsystem.entities.InvoiceProduct;
import com.system.billingsystem.entities.Product;

public class InvoiceProductDtoMapper {

    private InvoiceProductDtoMapper() {}

    public static InvoiceProductDto toDto(InvoiceProduct invoiceProduct) {
        return new InvoiceProductDto(
                invoiceProduct.getInvoiceProduct_id(),
                invoiceProduct.getProduct().getName(),
                invoiceProduct.getAmount(),
                invoiceProduct.getProduct().getProduct_id(),
                invoiceProduct.getInvoice().getInvoice_id()
        );
    }

    public static InvoiceProduct toDomain(InvoiceProductDto invoiceProductDto) {
        InvoiceProduct invoiceProduct = new InvoiceProduct();

        invoiceProduct.setInvoiceProduct_id(invoiceProductDto.invoiceProductDto_id());
        invoiceProduct.setAmount(invoiceProduct.getAmount());

        invoiceProduct.setProduct(new Product(invoiceProductDto.idProduct()));
        invoiceProduct.setInvoice(new Invoice(invoiceProductDto.idInvoice()));

        return invoiceProduct;
    }
}
