package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.InvoiceProductDto;
import com.system.billingsystem.entities.Invoice;
import com.system.billingsystem.entities.InvoiceProduct;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.entities.microtypes.ids.InvoiceProductId;

public class InvoiceProductDtoMapper {

    private InvoiceProductDtoMapper() {}

    public static InvoiceProductDto toDto(InvoiceProduct invoiceProduct) {
        return new InvoiceProductDto(
                invoiceProduct.getInvoiceProductId().getValue(),
                invoiceProduct.getCount(),
                ProductDtoMapper.toDto(invoiceProduct.getProduct())
        );
    }

    public static InvoiceProduct toDomain(InvoiceProductDto invoiceProductDto, Invoice invoice, Product product) {
        InvoiceProduct invoiceProduct = new InvoiceProduct();

        invoiceProduct.setInvoiceProductId(new InvoiceProductId(invoiceProductDto.invoiceProductId()));
        invoiceProduct.setCount(invoiceProduct.getCount());

        invoiceProduct.setProduct(product);
        invoiceProduct.setInvoice(invoice);

        return invoiceProduct;
    }
}
