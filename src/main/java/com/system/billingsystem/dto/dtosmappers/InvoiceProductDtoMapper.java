package com.system.billingSystem.dto.dtosmappers;

import com.system.billingSystem.dto.InvoiceProductDto;
import com.system.billingSystem.entities.Invoice;
import com.system.billingSystem.entities.InvoiceProduct;
import com.system.billingSystem.entities.Product;

public class InvoiceProductDtoMapper {

    private InvoiceProductDtoMapper() {}

    public static InvoiceProductDto toDto(InvoiceProduct invoiceProduct) {
        return new InvoiceProductDto(
                invoiceProduct.getInvoiceproduct_id(),
                invoiceProduct.getProduct().getName(),
                invoiceProduct.getAmount(),
                invoiceProduct.getProduct().getProduct_id(),
                invoiceProduct.getInvoice().getInvoice_id()
        );
    }

    public static InvoiceProduct toDomain(InvoiceProductDto invoiceProductDto) {
        InvoiceProduct invoiceProduct = new InvoiceProduct();

        invoiceProduct.setInvoiceproduct_id(invoiceProductDto.invoiceProductDto_id());
        invoiceProduct.setAmount(invoiceProduct.getAmount());

        invoiceProduct.setProduct(new Product(invoiceProductDto.idProduct()));
        invoiceProduct.setInvoice(new Invoice(invoiceProductDto.idInvoice()));

        return invoiceProduct;
    }
}
