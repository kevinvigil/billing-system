package com.system.billingsystem.integrations.usecases;

import com.system.billingsystem.integrations.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.UUID;

public class CreateInvoiceIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldCreateInvoice() {

        var invoiceId = UUID.randomUUID();

        Object requestBody = String.format(
                """
                   {
                   "invoiceId": "%s",
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
                """, invoiceId
        );

        var response = webTestClient.post()
                .uri("/api/invoice/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .exchange();

        response.expectStatus().isCreated().expectBody();
    }
}
