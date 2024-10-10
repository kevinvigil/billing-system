package com.system.billingsystem.integrations.usecases.product.crud;

import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetProductIntegrationTest extends BaseIntegrationTest {

    private ProductId productId;

    @BeforeEach
    public void setUp() {
        // Given
        Object requestBody = """
        {
            "name": "Product",
            "description": "Description",
            "count": "10",
            "price": "100"
            }
        """;

        productId = webTestClient.post()
                .uri("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ProductId.class)
                .returnResult()
                .getResponseBody();

        Object requestBody2 = """
        {
            "name": "Product2",
            "description": "Description2",
            "count": "20",
            "price": "200"
            }
        """;

        webTestClient.post()
                .uri("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody2)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ProductId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(productId);
    }

    @Test
    public void shouldGetProductById() {
        // When
        var response = webTestClient.get()
                .uri("/api/product/"+productId.getValue())
                .exchange().expectStatus().isOk();

        // Then

        assertNotNull(response);
        response.expectBody()
                .jsonPath("$.productId").isEqualTo(productId.getValue().toString())
                .jsonPath("$.name").isEqualTo("Product")
                .jsonPath("$.description").isEqualTo("Description")
                .jsonPath("$.count").isEqualTo(10)
                .jsonPath("$.price").isEqualTo("100.0");
    }

    @Test
    public void shouldGetAllProducts() {
        // When
        var response = webTestClient.get()
                .uri("/api/product/")
                .exchange();

        // Then
        assertNotNull(response);
        response.expectStatus().isOk();
        List resp = response.expectBody(List.class).returnResult().getResponseBody();
        assertEquals(2, resp.size());
    }
}
