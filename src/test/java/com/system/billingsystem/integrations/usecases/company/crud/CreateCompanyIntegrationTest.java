package com.system.billingsystem.integrations.usecases.company.crud;

import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateCompanyIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldCreateNewCompany() {
        // Given

        Object requestBody = """
        {
            "name": "company",
            "phone": "+549 2287 445566",
            "cuit": "22222",
            "email": "company1@gmail.com",
            "address": "country, state, city, zip",
            "soldInvoices": [],
            "purchasedInvoices": []
        }
        """;

        // When
        var response = webTestClient.post()
                .uri("/api/company/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange();

        // Then
        assertNotNull(response);
        response.expectStatus().isCreated();
    }
}
