package com.system.billingsystem.integrations.usecases.product.crud;

import com.system.billingsystem.dto.ProductDto;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeleteProductIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldDeleteProduct() {
        // Given
        Object requestBody = """
        {
            "name": "Product",
            "description": "Description",
            "count": "10",
            "price": "100"
            }
        """;

        ProductId productId = webTestClient.post()
                .uri("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ProductId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(productId);

        // When
        var response = webTestClient.delete()
                .uri("/api/product/" + productId.getValue().toString())
                .exchange();

        // Then
        assertNotNull(response);
        response.expectBody()
                .jsonPath("$.productId").isEqualTo(productId.getValue().toString())
                .jsonPath("$.name").isEqualTo("Product")
                .jsonPath("$.description").isEqualTo("Description")
                .jsonPath("$.count").isEqualTo(10)
                .jsonPath("$.price").isEqualTo("100.0");

        webTestClient.get().uri("/api/product/" + productId.getValue().toString())
                .exchange().expectStatus().isNotFound();
    }
}
