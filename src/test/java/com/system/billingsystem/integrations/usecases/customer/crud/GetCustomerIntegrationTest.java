package com.system.billingsystem.integrations.usecases.customer.crud;

import com.system.billingsystem.entities.microtypes.ids.CustomerId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetCustomerIntegrationTest extends BaseIntegrationTest {

    private CustomerId customerId;

    @BeforeEach
    public void setUp() {
        // Given

        Object requestBody = """
        {
            "name": "firstName secondName surname",
            "email": "customerEmail@gmail.com",
            "password": "password",
            "company": null
        }
        """;

        customerId = webTestClient.post()
                .uri("/api/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustomerId.class)
                .returnResult()
                .getResponseBody();

        Object requestBody2 = """
        {
            "name": "firstName secondName surname",
            "email": "customerEmail2@gmail.com",
            "password": "password",
            "company": null
        }
        """;

        webTestClient.post()
                .uri("/api/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody2)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustomerId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(customerId);
    }

    @Test
    public void shouldGetCustomerById() {
        // When
        var response = webTestClient.get()
                .uri("/api/customer/"+customerId.getValue())
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
    }

    @Test
    public void shouldGetAllCustomers() {
        // When
        var response = webTestClient.get()
                .uri("/api/customer/").exchange();

        // Then
        assertNotNull(response);

        List resp = response.expectBody(List.class).returnResult().getResponseBody();

        assertNotNull(resp);

        assertEquals(2, resp.size());
    }

}
