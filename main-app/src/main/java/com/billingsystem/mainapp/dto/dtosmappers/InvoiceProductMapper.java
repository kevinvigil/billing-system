package com.billingsystem.mainapp.dto.dtosmappers;

import com.billingsystem.mainapp.dto.InvoiceProductDto;
import com.billingsystem.mainapp.entities.InvoiceProduct;
import com.billingsystem.mainapp.entities.Product;
import com.billingsystem.mainapp.entities.microtypes.ids.ProductId;
import com.billingsystem.mainapp.entities.microtypes.names.ProductName;
import com.billingsystem.mainapp.entities.microtypes.prices.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.UUID;
@Mapper
public interface InvoiceProductMapper {

    InvoiceProductMapper INVOICE_PRODUCT_MAPPER = Mappers.getMapper(InvoiceProductMapper.class);

    @Mappings({
            @Mapping(target = "productId", expression = "java(mapProductId(invoiceProduct))"),
            @Mapping(target = "name", expression = "java(mapProductName(invoiceProduct))"),
            @Mapping(target = "description", expression = "java(mapProductDescription(invoiceProduct))"),
            @Mapping(target = "price", expression = "java(mapProductPrice(invoiceProduct))"),
            @Mapping(target = "count", expression = "java(invoiceProduct.getCount())")
    })
    InvoiceProductDto toDto(InvoiceProduct invoiceProduct);



    @Mappings({
            @Mapping(target = "product", expression = "java(mapProduct(dto))"),
            @Mapping(target = "count", source = "dto.count"),
            @Mapping(target = "invoice", ignore = true)
    })
    InvoiceProduct toDomain(InvoiceProductDto dto);

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
