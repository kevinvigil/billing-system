package com.system.billingsystem.integrations.usecases.product.crud;

import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateProductIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldUpdateProduct() {
        // Given

        Object postRequestBody = """
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
                .bodyValue(postRequestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ProductId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(productId);

        Object requestBody = String.format("""
        {
            "productId": "%s",
            "name": "Product",
            "description": "Description",
            "count": "1",
            "price": "100"
            }
        """, productId.getValue());

        // When
        webTestClient.put()
                .uri("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isOk();

        var response = webTestClient.get()
                .uri("/api/product/"+productId.getValue())
                .exchange();

        // Then
        response.expectBody()
                .jsonPath("$.productId").isEqualTo(productId.getValue().toString())
                .jsonPath("$.name").isEqualTo("Product")
                .jsonPath("$.description").isEqualTo("Description")
                .jsonPath("$.count").isEqualTo(1)
                .jsonPath("$.price").isEqualTo("100.0");

    }
}
