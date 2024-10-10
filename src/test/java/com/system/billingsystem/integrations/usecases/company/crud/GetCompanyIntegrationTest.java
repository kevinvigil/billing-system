package com.system.billingsystem.integrations.usecases.company.crud;

import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetCompanyIntegrationTest extends BaseIntegrationTest {

    private CompanyId companyId;

    @BeforeEach
    public void setUp() {
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

        companyId = webTestClient.post()
                .uri("/api/company/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CompanyId.class)
                .returnResult()
                .getResponseBody();

        Object requestBody2 = """
        {
            "name": "company",
            "phone": "+549 2287 445577",
            "cuit": "33333",
            "email": "company2@gmail.com",
            "address": "country, state, city, zip",
            "soldInvoices": [],
            "purchasedInvoices": []
        }
        """;

        webTestClient.post()
                .uri("/api/company/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody2)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CompanyId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(companyId);
    }

    @Test
    public void shouldGetCompanyById() {
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

    @Test
    public void shouldGetAllCompanies() {
        // When
        var response = webTestClient.get()
                .uri("/api/company/").exchange();

        // Then
        response.expectStatus().isOk();

        List resp = response.expectBody(List.class).returnResult().getResponseBody();

        assertNotNull(resp);

        assertEquals(2, resp.size());
    }
}
