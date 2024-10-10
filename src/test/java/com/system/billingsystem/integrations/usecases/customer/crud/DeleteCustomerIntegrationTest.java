package com.system.billingsystem.integrations.usecases.customer.crud;

import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeleteCustomerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldDeleteCustomer() {
        // Given

        Object requestBody = """
        {
            "name": "firstName secondName surname",
            "email": "customerEmail@gmail.com",
            "password": "password",
            "company": null
        }
        """;

        CustomerId customerId = webTestClient.post()
                .uri("/api/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustomerId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(customerId);

        // When
        var response = webTestClient.delete()
                .uri("/api/customer/"+customerId.getValue().toString())
                .exchange();

        // Then
        assertNotNull(response);
        response.expectStatus().isOk()
                .expectBody()
                .jsonPath("$.customerId").isEqualTo(customerId.getValue().toString())
                .jsonPath("$.email").isEqualTo("customerEmail@gmail.com")
                .jsonPath("$.password").isEqualTo("password")
                .jsonPath("$.name").isEqualTo("firstName secondName surname")
                .jsonPath("$.company").isEqualTo(null);

        webTestClient.get().uri("/api/customer/"+customerId.getValue().toString())
                .exchange().expectStatus().isNotFound();
    }
}
