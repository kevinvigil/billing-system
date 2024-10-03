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

        CompanyId companyId = webTestClient.post()
            .uri("/api/company/")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(CompanyId.class)
             .returnResult()
             .getResponseBody();

        assertNotNull(companyId);

        // When
        var response = webTestClient.get()
            .uri("/api/company/"+companyId.getValue())
            .exchange();

        // Then
        response.expectStatus().isOk()
                .expectBody()
                .jsonPath("$.companyId").isEqualTo(companyId.getValue().toString())
                .jsonPath("$.name").isEqualTo("company")
                .jsonPath("$.phone").isEqualTo("+549 2287 445566")
                .jsonPath("$.cuit").isEqualTo("22222")
                .jsonPath("$.email").isEqualTo("company1@gmail.com")
                .jsonPath("$.address").isEqualTo("country, state, city, zip")
                .jsonPath("$.soldInvoices").isArray()
                .jsonPath("$.purchasedInvoices").isArray();

    }
}
