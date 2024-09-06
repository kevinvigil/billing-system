package com.system.billingsystem.dtomappers;

import com.system.billingsystem.dto.ProductDto;
import com.system.billingsystem.dto.dtosmappers.ProductMapper;
import com.system.billingsystem.dto.dtosmappers.ProductMapperImpl;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.entities.builders.productbuilder.ProductBuilder;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.entities.microtypes.names.ProductName;
import com.system.billingsystem.entities.microtypes.prices.ProductPrice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static com.system.billingsystem.dto.dtosmappers.ProductMapper.PRODUCT_MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductMapperTest {

    private static Product product;
    private static ProductDto productDto;

    @BeforeAll
    public static void setUpBeforeClass() {
        // Given
        UUID baseId = UUID.randomUUID();

        product = ProductBuilder.newBuilder()
                .productId(new ProductId(baseId))
                .name(new ProductName("name 1"))
                .description("description")
                .price(new ProductPrice(new BigDecimal(100)))
                .count(10)
                .build();

        productDto = new ProductDto(
                baseId,
                "name 1",
                10,
                "description",
                new BigDecimal(100)
        );
    }

    @Test
    public void shouldMapProductToDto(){
        // Then
        ProductDto newProductDto = PRODUCT_MAPPER.toDto(product);

        // When
        assertEquals(productDto, newProductDto);
    }

    @Test
    public void shouldMapDtoToProduct(){
        // Then
        Product newProduct = PRODUCT_MAPPER.toDomain(productDto);

        // When
        assertEquals(product, newProduct);
    }
}
