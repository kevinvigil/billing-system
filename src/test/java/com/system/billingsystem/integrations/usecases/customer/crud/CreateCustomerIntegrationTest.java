package com.system.billingsystem.integrations.usecases.customer.crud;

import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CreateCustomerIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldCreateNewCustomer() {

        Object requestBody = """
        {
            "name": "firstName secondName surname",
            "email": "customerEmail@gmail.com",
            "password": "password",
            "company": null
        }
        """;

        var response = webTestClient.post().uri("/api/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange();

        assertNotNull(response);
        response.expectStatus().isCreated();

    }
}
