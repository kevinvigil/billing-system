package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.InvoiceProductDto;
import com.system.billingsystem.entities.Invoice;
import com.system.billingsystem.entities.InvoiceProduct;
import com.system.billingsystem.entities.Product;

public class InvoiceProductDtoMapper {

    private InvoiceProductDtoMapper() {}

    public static InvoiceProductDto toDto(InvoiceProduct invoiceProduct) {
        return new InvoiceProductDto(
                invoiceProduct.getId(),
                invoiceProduct.getProduct().getName(),
                invoiceProduct.getAmount(),
                invoiceProduct.getProduct().getId(),
                invoiceProduct.getInvoice().getId()
        );
    }

    public static InvoiceProduct toDomain(InvoiceProductDto invoiceProductDto) {
        InvoiceProduct invoiceProduct = new InvoiceProduct();

        invoiceProduct.setId(invoiceProductDto.id());
        invoiceProduct.setAmount(invoiceProduct.getAmount());

        invoiceProduct.setProduct(new Product(invoiceProductDto.idProduct()));
        invoiceProduct.setInvoice(new Invoice(invoiceProductDto.idInvoice()));

        return invoiceProduct;
    }
}
