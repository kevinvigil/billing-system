package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.InvoiceProductDto;
import com.system.billingsystem.entities.InvoiceProduct;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.entities.microtypes.ids.InvoiceProductId;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.entities.microtypes.names.ProductName;
import com.system.billingsystem.entities.microtypes.prices.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;
import java.util.UUID;
@Mapper
public interface InvoiceProductMapper {

    @Mappings({
            @Mapping(target = "invoiceProductId", expression = "java(mapInvoiceProductIdDto(invoiceProduct))"),
            @Mapping(target = "productId", expression = "java(mapProductId(invoiceProduct))"),
            @Mapping(target = "name", expression = "java(mapProductName(invoiceProduct))"),
            @Mapping(target = "description", expression = "java(mapProductDescription(invoiceProduct))"),
            @Mapping(target = "price", expression = "java(mapProductPrice(invoiceProduct))"),
            @Mapping(target = "count", source = "invoiceProduct.count")
    })
    InvoiceProductDto toDto(InvoiceProduct invoiceProduct);



    @Mappings({
            @Mapping(target = "invoiceProductId", expression = "java(mapInvoiceProductId(dto))"),
            @Mapping(target = "product", expression = "java(mapProduct(dto))"),
            @Mapping(target = "count", source = "dto.count"),
            @Mapping(target = "invoice", ignore = true) // Ignoramos Invoice como lo solicitaste
    })
    InvoiceProduct toDomain(InvoiceProductDto dto);

    default UUID mapInvoiceProductIdDto(InvoiceProduct invoiceProduct) {
        return invoiceProduct.getInvoiceProductId() != null ? invoiceProduct.getInvoiceProductId().getValue() : null;
    }

    default InvoiceProductId mapInvoiceProductId(InvoiceProductDto dto) {
        return new InvoiceProductId(dto.invoiceProductId());
    }

    default UUID mapProductId(InvoiceProduct invoiceProduct) {
        return invoiceProduct.getProduct() != null ? invoiceProduct.getProduct().getProductId().getValue() : null;
    }

    default String mapProductName(InvoiceProduct invoiceProduct) {
        return invoiceProduct.getProduct() != null ? invoiceProduct.getProduct().getName().getName() : null;
    }

    default String mapProductDescription(InvoiceProduct invoiceProduct) {
        return invoiceProduct.getProduct() != null ? invoiceProduct.getProduct().getDescription() : null;
    }

    default BigDecimal mapProductPrice(InvoiceProduct invoiceProduct) {
        return invoiceProduct.getProduct() != null ? invoiceProduct.getProduct().getPrice().getValue() : null;
    }

    default Product mapProduct(InvoiceProductDto dto) {
        return new Product(new ProductId(dto.productId()),
                new ProductName(dto.name()), dto.description(),
                new ProductPrice(dto.price()));
    }
}
