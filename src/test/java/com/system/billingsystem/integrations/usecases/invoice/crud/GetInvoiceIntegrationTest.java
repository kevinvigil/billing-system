package com.system.billingsystem.integrations.usecases.invoice.crud;

import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetInvoiceIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldGetInvoiceById() {
        // Given

        Object requestBody = """
        {
           "date": "11111111111111",
           "paid": false,
           "invoiced": false,
           "price": "2",
           "discount": "0",
           "currency": "ARS",
           "invoiceVoucher": "REFERENCE",
           "category": "A",
           "sellerCompany": null,
           "buyerCompany": null,
           "products": []
        }
        """;

        InvoiceId id = webTestClient.post()
                .uri("/api/invoice/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(InvoiceId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(id);

        // When
        var response = webTestClient.get()
                .uri("/api/invoice/"+id.getValue())
                .exchange();

        // Then
        response.expectStatus().isOk()
                .expectBody().jsonPath("$.invoiceId").isEqualTo(id.getValue().toString());
    }
}
