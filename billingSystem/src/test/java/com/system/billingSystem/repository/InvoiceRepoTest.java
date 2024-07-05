package com.system.billingSystem.repository;

import com.system.billingSystem.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class InvoiceRepoTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    private Invoice invoice;

    @BeforeEach
    void setUp(){
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        invoice = Invoice.builder()
                .id(1L)
                .date(offsetDateTime)
                .invoiceVoucher(InvoiceVoucher.FACTURA)
                .invoiced(false)
                .paid(false)
                .type(InvoiceType.A)
                .customer(null)
                .company(null)
                .total(0).build();

        invoiceRepository.save(invoice);
    }

    @AfterEach
    void tearDown(){
        invoiceRepository.deleteAll();
    }

    @Test
    void testSave(){
        Invoice newInvoice = invoiceRepository.save(invoice);

        assertThat(newInvoice).isNotNull();
        assertThat(newInvoice.getId()).isNotNull();

        assertThat(newInvoice.getId()).isGreaterThan(0);
    }

    /*
     * If it's executed individually it isn't has errors, but when all the class is executed it has errors
    */
    @Test
    void testFindById(){

        Invoice invoice1 = invoiceRepository.findById(invoice.getId()).orElse(null);
        System.out.println(invoice.getId());

        assertThat(invoice1).isNotNull();

        System.out.println(invoice1.getId());

        assertEquals(invoice1.getId(), invoice.getId());
    }

    @Test
    void testDelete(){

        invoiceRepository.deleteById(invoice.getId());

        Invoice invoice1 = Optional.of(invoiceRepository.findById(invoice.getId())).get().orElse(null);

        assertThat(invoice1).isNull();
    }
}