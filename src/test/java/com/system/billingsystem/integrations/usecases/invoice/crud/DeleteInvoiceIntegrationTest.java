package com.system.billingsystem.integrations.usecases.invoice.crud;

import com.system.billingsystem.dto.InvoiceDto;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeleteInvoiceIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldDeleteInvoice() {
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

        InvoiceId invoiceId = webTestClient.post()
                .uri("/api/invoice/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(InvoiceId.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(invoiceId);

        // When
        var response = webTestClient.delete()
                .uri("/api/invoice/"+invoiceId.getValue().toString())
                .exchange();

        // Then
        assertNotNull(response);
        response.expectStatus().isOk()
                .expectBody()
                .jsonPath("$.invoiceId").isEqualTo(invoiceId.getValue().toString())
                .jsonPath("$.date").isEqualTo("11111111111111")
                .jsonPath("$.paid").isEqualTo(false)
                .jsonPath("$.invoiced").isEqualTo(false)
                .jsonPath("$.price").isEqualTo("0.0")
                .jsonPath("$.discount").isEqualTo("0")
                .jsonPath("$.currency").isEqualTo("ARS")
                .jsonPath("$.invoiceVoucher").isEqualTo("REFERENCE")
                .jsonPath("$.category").isEqualTo("A")
                .jsonPath("$.sellerCompany").isEqualTo(null)
                .jsonPath("$.buyerCompany").isEqualTo(null)
                .jsonPath("$.products").isArray();

        webTestClient.get()
                .uri("/api/invoice/"+invoiceId.getValue().toString())
                .exchange().expectStatus().isNotFound();
    }
}
