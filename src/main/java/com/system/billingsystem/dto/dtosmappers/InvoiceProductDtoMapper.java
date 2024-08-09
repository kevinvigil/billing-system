package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.InvoiceProductDto;
import com.system.billingsystem.entities.Invoice;
import com.system.billingsystem.entities.InvoiceProduct;
import com.system.billingsystem.entities.Product;

public class InvoiceProductDtoMapper {

    private InvoiceProductDtoMapper() {}

    public static InvoiceProductDto toDto(InvoiceProduct invoiceProduct) {
        return new InvoiceProductDto(
                invoiceProduct.getInvoiceProductId(),
                invoiceProduct.getProduct().getName(),
                invoiceProduct.getCount(),
                invoiceProduct.getProduct().getProductId(),
                invoiceProduct.getInvoice().getInvoiceId()
        );
    }

    public static InvoiceProduct toDomain(InvoiceProductDto invoiceProductDto) {
        InvoiceProduct invoiceProduct = new InvoiceProduct();

        invoiceProduct.setInvoiceProductId(invoiceProductDto.invoiceProductId());
        invoiceProduct.setCount(invoiceProduct.getCount());

        invoiceProduct.setProduct(new Product(invoiceProductDto.productId()));
        invoiceProduct.setInvoice(new Invoice(invoiceProductDto.invoiceId()));

        return invoiceProduct;
    }
}
