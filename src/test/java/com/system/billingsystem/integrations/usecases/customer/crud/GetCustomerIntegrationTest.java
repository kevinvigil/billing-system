package com.system.billingsystem.integrations.usecases.customer.crud;

import com.system.billingsystem.entities.microtypes.ids.CompanyId;
import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetCustomerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldGetCustomerById() {
        // Given

        Object requestBody = """
        {
            "name": "firstName secondName surname",
            "email": "customerEmail@gmail.com",
            "password": "password",
            "company": null
        }
        """;

        CustomerId id = webTestClient.post()
                .uri("/api/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustomerId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(id);

        // When
        var response = webTestClient.get()
                .uri("/api/customer/"+id.getValue())
                .exchange();

        // Then
        response.expectStatus().isOk()
                .expectBody().jsonPath("$.customerId").isEqualTo(id.getValue().toString());
    }

}
