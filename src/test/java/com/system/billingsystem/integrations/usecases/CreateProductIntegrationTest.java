package com.system.billingsystem.integrations.usecases;

import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

public class CreateProductIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldCreateProduct() {

        UUID productId = UUID.randomUUID();

        var requestBody = String.format(
                """
                    {
                    "productId": "%s",
                    "name": "Product",
                    "description": "Description",
                    "count": "10",
                    "price": "100"
                    }
                """, productId
        );

        var responce = webTestClient.post()
                .uri("/api/product/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody).exchange();

        responce.expectStatus().isCreated();
    }
}
