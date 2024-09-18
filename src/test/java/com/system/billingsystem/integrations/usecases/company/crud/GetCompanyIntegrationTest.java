package com.system.billingsystem.integrations.usecases.company.crud;

import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetCompanyIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldGetCompanyById() {
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

        CompanyId id = webTestClient.post()
            .uri("/api/company/")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(CompanyId.class)
             .returnResult()
             .getResponseBody();

        assertNotNull(id);

        // When
        var response = webTestClient.get()
            .uri("/api/company/"+id.getValue())
            .exchange();

        // Then
        response.expectStatus().isOk()
            .expectBody().jsonPath("$.companyId").isEqualTo(id.getValue().toString());
    }
}
