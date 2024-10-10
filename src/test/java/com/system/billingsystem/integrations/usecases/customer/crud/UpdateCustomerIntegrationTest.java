package com.system.billingsystem.integrations.usecases.customer.crud;

import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateCustomerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldUpdateCustomer() {
        // Given

        Object postRequestBody = """
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
                .bodyValue(postRequestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustomerId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(customerId);

        Object putRequestBody = String.format("""
        {
            "customerId": "%s",
            "name": "firstName secondName surname",
            "email": "newCustomerEmail@gmail.com",
            "password": "password",
            "company": null
        }
        """, customerId.getValue());

        //When
        webTestClient.put()
                .uri("/api/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(putRequestBody)
                .exchange()
                .expectStatus().isNoContent();

        var response = webTestClient.get()
                .uri("/api/customer/"+customerId.getValue())
                .exchange();

        // Then
        assertNotNull(response);
        response.expectStatus().isOk()
                .expectBody()
                .jsonPath("$.customerId").isEqualTo(customerId.getValue().toString())
                .jsonPath("$.email").isEqualTo("newCustomerEmail@gmail.com")
                .jsonPath("$.password").isEqualTo("password")
                .jsonPath("$.name").isEqualTo("firstName secondName surname")
                .jsonPath("$.company").isEqualTo(null);
    }
}
