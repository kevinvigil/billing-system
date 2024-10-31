package com.billingsystem.mainapp.dtomappers;

import com.billingsystem.mainapp.dto.ProductDto;
import com.billingsystem.mainapp.dto.dtosmappers.ProductMapper;
import com.billingsystem.mainapp.entities.Product;
import com.billingsystem.mainapp.entities.builders.productbuilder.ProductBuilder;
import com.billingsystem.mainapp.entities.microtypes.ids.ProductId;
import com.billingsystem.mainapp.entities.microtypes.names.ProductName;
import com.billingsystem.mainapp.entities.microtypes.prices.ProductPrice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static com.billingsystem.mainapp.dto.dtosmappers.ProductMapper.PRODUCT_MAPPER;
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
        assertEquals(productDto.productId(), newProductDto.productId());
        assertEquals(productDto.name(), newProductDto.name());
        assertEquals(productDto.description(), newProductDto.description());
        assertEquals(productDto.price(), newProductDto.price());
        assertEquals(productDto.count(), newProductDto.count());
    }

    @Test
    public void shouldMapDtoToProduct(){
        // Then
        Product newProduct = PRODUCT_MAPPER.toDomain(productDto);

        // When
        assertEquals(product.getProductId(), newProduct.getProductId());
        assertEquals(product.getName(), newProduct.getName());
        assertEquals(product.getDescription(), newProduct.getDescription());
        assertEquals(product.getPrice(), newProduct.getPrice());
        assertEquals(product.getCount(), newProduct.getCount());
    }
}
