package com.system.billingsystem.integrations.usecases.invoice.crud;

import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateInvoiceIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldUpdateInvoice() {

        // Given
        Object postRequestBody = """
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

        InvoiceId invoiceId = webTestClient.post()
                .uri("/api/invoice/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(postRequestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(InvoiceId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(invoiceId);

        Object putRequestBody = String.format("""
        {
           "invoiceId": "%s",
           "date": "11111111111111",
           "paid": false,
           "invoiced": false,
           "price": "2",
           "discount": "0",
           "currency": "ARS",
           "invoiceVoucher": "CASH",
           "category": "A",
           "sellerCompany": null,
           "buyerCompany": null,
           "products": []
        }
        """, invoiceId.getValue());

        // When
        webTestClient.put()
                .uri("/api/invoice/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(putRequestBody)
                .exchange()
                .expectStatus().isOk();

        var response = webTestClient.get()
                .uri("/api/invoice/"+invoiceId.getValue())
                .exchange().expectStatus().isOk();

        // Then
        response.expectBody()
                .jsonPath("$.invoiceId").isEqualTo(invoiceId.getValue().toString())
                .jsonPath("$.date").isEqualTo("11111111111111")
                .jsonPath("$.paid").isEqualTo(false)
                .jsonPath("$.invoiced").isEqualTo(false)
                .jsonPath("$.price").isEqualTo("0.0")
                .jsonPath("$.discount").isEqualTo("0")
                .jsonPath("$.currency").isEqualTo("ARS")
                .jsonPath("$.invoiceVoucher").isEqualTo("CASH")
                .jsonPath("$.category").isEqualTo("A")
                .jsonPath("$.sellerCompany").isEqualTo(null)
                .jsonPath("$.buyerCompany").isEqualTo(null)
                .jsonPath("$.products").isArray();
    }
}
