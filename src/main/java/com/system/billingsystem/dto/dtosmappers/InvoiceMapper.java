package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CompanyDto;
import com.system.billingsystem.dto.InvoiceDto;
import com.system.billingsystem.dto.InvoiceProductDto;
import com.system.billingsystem.entities.*;
import com.system.billingsystem.entities.microtypes.Discount;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.entities.microtypes.names.ProductName;
import com.system.billingsystem.entities.Currency;
import com.system.billingsystem.entities.microtypes.prices.InvoicePrice;
import com.system.billingsystem.entities.microtypes.prices.ProductPrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper
public interface InvoiceMapper {

    InvoiceMapper INVOICE_MAPPER = Mappers.getMapper(InvoiceMapper.class);

    @Mappings({
            @Mapping(target = "invoiceId", expression = "java(invoice.getInvoiceId().getValue())"),
            @Mapping(target = "date", expression = "java(invoice.getDate())"),
            @Mapping(target = "paid", expression = "java(invoice.isPaid())"),
            @Mapping(target = "invoiced", expression = "java(invoice.isInvoiced())"),
            @Mapping(target = "price", expression = "java(mapInvoicePriceToDto(invoice.getPrice()))"),
            @Mapping(target = "currency", expression = "java(invoice.getPrice().getCurrency().name())"),
            @Mapping(target = "discount", expression = "java(invoice.getDiscount().getDiscount())"),
            @Mapping(target = "invoiceVoucher", expression = "java(invoice.getInvoicevoucher().toString())"),
            @Mapping(target = "category", source = "invoice.category"),
            @Mapping(target = "sellerCompany", expression = "java(mapCompany(invoice.getSellerCompany()))"),
            @Mapping(target = "buyerCompany", expression = "java(mapCompany(invoice.getBuyerCompany()))"),
            @Mapping(target = "products", expression = "java(mapProductsDto(invoice))")
    })
    InvoiceDto toDto(Invoice invoice);

    default List<InvoiceProductDto> mapProductsDto (Invoice invoice){
        if(invoice == null || invoice.getProducts() == null || invoice.getProducts().isEmpty()) return new ArrayList<InvoiceProductDto>();
        return invoice.getProducts().stream().map(invoiceProduct -> new InvoiceProductDto(
                invoiceProduct.getInvoiceProductId().getValue(),
                invoiceProduct.getProduct().getProductId().getValue(),
                invoiceProduct.getCount(),
                invoiceProduct.getProduct().getName().getName(),
                invoiceProduct.getProduct().getDescription(),
                invoiceProduct.getProduct().getPrice().getValue()
        )).toList();
    }

    @Mappings({
            @Mapping(target = "invoiceId", expression = "java(mapInvoiceId(dto.invoiceId()))"),
            @Mapping(target = "date", expression = "java(dto.date())"),
            @Mapping(target = "paid", expression = "java(dto.paid())"),
            @Mapping(target = "invoiced", expression = "java(dto.invoiced())"),
            @Mapping(target = "price", expression = "java(mapInvoicePrice(dto.price()))"),
            @Mapping(target = "discount", expression = "java(mapDiscount(dto))"),
            @Mapping(target = "invoicevoucher", source = "dto.invoiceVoucher"),
            @Mapping(target = "category", source = "dto.category"),
            @Mapping(target = "sellerCompany", expression = "java(mapCompany(dto.sellerCompany()))"),
            @Mapping(target = "buyerCompany", expression = "java(mapCompany(dto.buyerCompany()))"),
            @Mapping(target = "products", expression = "java(mapProductsDomain(dto))")
    })
    Invoice toDomain(InvoiceDto dto);

    default Discount mapDiscount(InvoiceDto dto){
        return new Discount(dto.discount());
    }

    default List<InvoiceProduct> mapProductsDomain(InvoiceDto dto){
        if(dto == null || dto.products() == null || dto.products().isEmpty()) return new ArrayList<InvoiceProduct>();
        return dto.products().stream().map(invoiceProductDto -> {
            Product product = new Product(
                    (invoiceProductDto.productId() == null)? null : new ProductId(invoiceProductDto.productId()),
                    (invoiceProductDto.name() == null)? null : new ProductName(invoiceProductDto.name()),
                    (invoiceProductDto.description() == null)? null : invoiceProductDto.description(),
                    (invoiceProductDto.price() == null)? null :
                            (dto.currency() == null)? new ProductPrice(invoiceProductDto.price()):
                                new ProductPrice(Currency.valueOf(dto.currency()), invoiceProductDto.price()));

            InvoiceProduct invoiceProduct = new InvoiceProduct();

            invoiceProduct.setProduct(product);
            invoiceProduct.setCount(invoiceProduct.getCount());

            return invoiceProduct;
        }).toList();
    }

    default InvoiceId mapInvoiceId(UUID invoiceId) {
        return invoiceId != null ? new InvoiceId(invoiceId) : null;
    }

    default BigDecimal mapInvoicePriceToDto(InvoicePrice invoicePrice) {
        return invoicePrice != null ? invoicePrice.getValue() : null;
    }

    default InvoicePrice mapInvoicePrice(BigDecimal price) {
        return price != null ? new InvoicePrice(price) : null;
    }

    default CompanyDto mapCompany(Company company) {
        return CompanyMapper.COMPANY_MAPPER.toDto(company);
    }

    default Company mapCompany(CompanyDto dto) {
        return CompanyMapper.COMPANY_MAPPER.toDomain(dto);
    }

}
