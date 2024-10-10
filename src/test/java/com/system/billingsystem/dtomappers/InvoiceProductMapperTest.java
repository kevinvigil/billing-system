package com.system.billingsystem.dtomappers;

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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import static com.system.billingsystem.dto.dtosmappers.InvoiceProductMapper.INVOICE_PRODUCT_MAPPER;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InvoiceProductMapperTest {

    private static Invoice invoice;
    private static Product product;

    private InvoiceProduct invoiceProduct;
    private InvoiceProductDto invoiceProductDto;

    private static final UUID baseId = UUID.randomUUID();

    @BeforeAll
    public static void setUpBeforeClass() {
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

        product = ProductBuilder.newBuilder()
                .productId(new ProductId(baseId))
                .name(new ProductName("name 1"))
                .description("description")
                .price(new ProductPrice(new BigDecimal(100)))
                .count(10)
                .build();
    }

    @BeforeEach
    public void setUp() {
        // Given
        invoiceProduct = InvoiceProductBuilder.newBuilder()
                .count(10)
                .invoice(invoice)
                .product(product)
                .build();

        invoiceProductDto = new InvoiceProductDto(
                product.getProductId().getValue(),
                10,
                product.getName().getName(),
                product.getDescription(),
                product.getPrice().getValue());
    }

    @Test
    public void shouldMapInvoiceProductToDto(){
        // Then
        InvoiceProductDto newInvoiceProductDto = INVOICE_PRODUCT_MAPPER.toDto(invoiceProduct);

        // When
        assertNotNull(newInvoiceProductDto);
        assertEquals(product.getProductId().getValue(), newInvoiceProductDto.productId());
        assertEquals(product.getName().getName(), newInvoiceProductDto.name());
        assertEquals(product.getDescription(), newInvoiceProductDto.description());
        assertEquals(product.getPrice().getValue(), newInvoiceProductDto.price());
        assertEquals(invoiceProduct.getCount(), newInvoiceProductDto.count());

    }

    @Test
    public void shouldMapDtoToInvoiceProduct(){
        // Then
        InvoiceProduct newInvoiceProduct = INVOICE_PRODUCT_MAPPER.toDomain(invoiceProductDto);

        // When
        assertNotNull(newInvoiceProduct);
        assertEquals(product.getProductId().getValue(), newInvoiceProduct.getProduct().getProductId().getValue());
        assertEquals(product.getName().getName(), newInvoiceProduct.getProduct().getName().getName());
        assertEquals(product.getDescription(), newInvoiceProduct.getProduct().getDescription());
        assertEquals(product.getPrice().getValue(), newInvoiceProduct.getProduct().getPrice().getValue());
        assertEquals(invoiceProduct.getCount(), newInvoiceProduct.getCount());

    }
}
