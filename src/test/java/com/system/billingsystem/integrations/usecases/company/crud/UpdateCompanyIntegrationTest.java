package com.system.billingsystem.integrations.usecases.company.crud;

import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateCompanyIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldUpdateCompany() {

        // Given
        Object postRequestBody = """
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
                .bodyValue(postRequestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CompanyId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(companyId);

        Object putRequestBody = String.format( """
                {
                    "companyId":"%s",
                    "name": "company",
                    "phone": "+549 2287 445566",
                    "cuit": "333333",
                    "email": "newcompany1@gmail.com",
                    "address": "country, state, city, zip",
                    "soldInvoices": [],
                    "purchasedInvoices": []
                }
                """, companyId.getValue());

        // When
        webTestClient.put()
                .uri("/api/company/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(putRequestBody)
                .exchange()
                .expectStatus().isOk();

        var response = webTestClient.get()
                .uri("/api/company/"+companyId.getValue())
                .exchange();

        // Then
        response.expectStatus().isOk()
                .expectBody()
                .jsonPath("$.companyId").isEqualTo(companyId.getValue().toString())
                .jsonPath("$.name").isEqualTo("company")
                .jsonPath("$.phone").isEqualTo("+549 2287 445566")
                .jsonPath("$.cuit").isEqualTo("333333")
                .jsonPath("$.email").isEqualTo("newcompany1@gmail.com")
                .jsonPath("$.address").isEqualTo("country, state, city, zip")
                .jsonPath("$.soldInvoices").isArray()
                .jsonPath("$.purchasedInvoices").isArray();
    }
}
