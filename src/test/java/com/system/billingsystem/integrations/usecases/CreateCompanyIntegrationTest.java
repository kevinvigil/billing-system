package com.system.billingsystem.integrations.usecases;

import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

public class CreateCompanyIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldCreateNewCompany() {
        // Given
        var companyId = UUID.randomUUID();

        Object requestBody = String.format(
                """
            {
                "companyId": "%s",
                "name": "company",
                "phone": "+549 2287 445566",
                "cuit": "22222",
                "email": "company1@gmail.com",
                "address": "country, state, city,",
                "soldInvoices": [],
                "purchasedInvoices": []
            }
            """, companyId);

        // When
        var response = webTestClient.post()
                .uri("/api/company/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange();

        // Then
        response.expectStatus().isCreated()
                .expectBody().equals(companyId.toString());
    }
}
