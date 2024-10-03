package com.system.billingsystem.integrations.usecases.product.crud;

import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateProductIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldCreateNewProduct() {
        // Given
        var requestBody = """
            {
            "name": "Product",
            "description": "Description",
            "count": "10",
            "price": "100"
            }
        """;

        // When
        var response = webTestClient.post()
                .uri("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody).exchange();

        // Then
        assertNotNull(response);
        response.expectStatus().isCreated();
    }
}
