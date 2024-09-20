package com.system.billingsystem.dtomappers;

import com.system.billingsystem.dto.InvoiceDto;
import com.system.billingsystem.dto.InvoiceProductDto;
import com.system.billingsystem.entities.*;
import com.system.billingsystem.entities.builders.invoicebuilder.InvoiceBuilder;
import com.system.billingsystem.entities.builders.invoiceproductbuilder.InvoiceProductBuilder;
import com.system.billingsystem.entities.builders.productbuilder.ProductBuilder;
import com.system.billingsystem.entities.microtypes.Discount;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.entities.microtypes.ids.InvoiceProductId;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.entities.microtypes.names.ProductName;
import com.system.billingsystem.entities.microtypes.prices.InvoicePrice;
import com.system.billingsystem.entities.microtypes.prices.ProductPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.system.billingsystem.dto.dtosmappers.InvoiceMapper.INVOICE_MAPPER;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceMapperTest {

    private Invoice invoice;
    private InvoiceDto invoiceDto;

    @BeforeEach
    public void setUp() {
        // Given
        UUID baseId = UUID.randomUUID();
        invoice = InvoiceBuilder.newBuilder()
                .invoiceId(new InvoiceId(baseId))
                .date(new Timestamp(111111))
                .paid(false)
                .invoiced(false)
                .price(new InvoicePrice(new BigDecimal(10)))
                .discount(new Discount(0))
                .invoiceVoucher(InvoiceVoucher.CASH)
                .category(InvoiceCategory.A)
                .sellerCompany(null)
                .buyerCompany(null)
                .ListInvoiceProducts(null)
                .build();

        invoiceDto = new InvoiceDto(
                baseId,
                new Timestamp(111111),
                false,
                false,
                new BigDecimal(10),
                "ARS",
                0,
                "CASH",
                "A",
                null, null, null
        );

    }

    @Test
    public void shouldMapInvoiceToDto() {
        // Then
        InvoiceDto dto = INVOICE_MAPPER.toDto(invoice);
        // When
        System.out.println(dto.price());
        assertEquals(invoiceDto, dto);
    }

    @Test
    public void shouldMapDtoToInvoice() {
        // Then
        Invoice newInvoice = INVOICE_MAPPER.toDomain(invoiceDto);
        // When
        assertEquals(invoice, newInvoice);
    }

    @Test void shouldMapInvoiceDtoToInvoiceWithProducts() {
        Product product = ProductBuilder.newBuilder()
                .productId(new ProductId(invoice.getInvoiceId().getValue()))
                .name(new ProductName("name 1"))
                .description("description")
                .price(new ProductPrice(new BigDecimal(100)))
                .count(10)
                .build();

        InvoiceProductDto invoiceProductDto = new InvoiceProductDto(
                product.getProductId().getValue(),
                10,
                product.getName().getName(),
                product.getDescription(),
                product.getPrice().getValue());

        List<InvoiceProductDto> invoiceProductDtoList = new ArrayList<>();
        invoiceProductDtoList.add(invoiceProductDto);

        invoiceDto = new InvoiceDto(
                invoice.getInvoiceId().getValue(),
                new Timestamp(111111),
                false,
                false,
                new BigDecimal(10),
                "ARS",
                0,
                "CASH",
                "A",
                null, null, invoiceProductDtoList
        );

        Invoice newInvoice = INVOICE_MAPPER.toDomain(invoiceDto);

        assertNotNull(newInvoice);
        assertNotNull(newInvoice.getProducts());
        assertEquals(1, newInvoice.getProducts().size());
    }

    @Test void shouldMapInvoiceToInvoiceDtoWithProducts() {
        Product product = ProductBuilder.newBuilder()
                .productId(new ProductId(UUID.randomUUID()))
                .name(new ProductName("name 1"))
                .description("description")
                .price(new ProductPrice(new BigDecimal(100)))
                .count(10)
                .build();

        InvoiceProduct invoiceProduct = InvoiceProductBuilder.newBuilder()
                .count(10)
                .invoice(invoice)
                .product(product)
                .build();

        List<InvoiceProduct> invoiceProductList = new ArrayList<>();
        invoiceProductList.add(invoiceProduct);
        invoice.setProducts(invoiceProductList);

        InvoiceDto newInvoiceDto = INVOICE_MAPPER.toDto(invoice);

        assertNotNull(newInvoiceDto);
        assertNotNull(newInvoiceDto.products());
        assertEquals(1, newInvoiceDto.products().size());
    }
}
