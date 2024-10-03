package com.system.billingsystem.integrations.usecases.invoice.crud;

import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateInvoiceIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldCreateNewInvoice() {
        //Given
        Object requestBody = """
           {
           "date": "11111111111111",
           "paid": false,
           "invoiced": false,
           "price": "0",
           "discount": "0",
           "currency": "ARS",
           "invoiceVoucher": "REFERENCE",
           "category": "A",
           "sellerCompany": null,
           "buyerCompany": null,
           "products": []
           }
        """;

        // When
        var response = webTestClient.post()
                .uri("/api/invoice/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange();

        // Then
        assertNotNull(response);
        response.expectStatus().isCreated();
    }
}
