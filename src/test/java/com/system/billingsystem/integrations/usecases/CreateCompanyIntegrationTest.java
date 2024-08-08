package com.system.billingsystem.integrations.usecases;

import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

public class CreateCompanyIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldCreateNewCompany() {
        // Given
        var companyId = UUID.randomUUID();
        String requestBody = String.format(
                """
                {
                    "companyId": "%s",
                    "name": "company",
                    "phone": "1111111",
                    "cuit": "22222",
                    "email": "company1@gmail.com",
                    "address": "some random, address, Argentina",
                    "soldInvoices": [],
                    "purchasedInvoices": []
                }
                """, companyId);

        var responseBody = "";

        // When
        var response = webTestClient.post()
                .uri("/company")
                .bodyValue(requestBody)
                .exchange();

        // Then
        response.expectStatus().isCreated()
                .expectBody().json(responseBody);
    }
}
