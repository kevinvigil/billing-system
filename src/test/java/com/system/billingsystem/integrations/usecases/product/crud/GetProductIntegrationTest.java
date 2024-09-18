package com.system.billingsystem.integrations.usecases.product.crud;

import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetProductIntegrationTest extends BaseIntegrationTest {
    @Test
    public void shouldGetInvoiceById() {
        // Given

        Object requestBody = """
        {
            "name": "Product",
            "description": "Description",
            "count": "10",
            "price": "100"
            }
        """;

        ProductId id = webTestClient.post()
                .uri("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(ProductId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(id);

        // When
        var response = webTestClient.get()
                .uri("/api/product/"+id.getValue())
                .exchange();

        // Then
        response.expectStatus().isOk()
                .expectBody().jsonPath("$.productId").isEqualTo(id.getValue().toString());
    }
}
