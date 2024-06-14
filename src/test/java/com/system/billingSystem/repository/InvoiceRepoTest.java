package com.system.billingSystem.repository;

import com.system.billingSystem.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InvoiceRepoTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    private Invoice invoice;

    @BeforeEach
    public void setUp(){
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        this.invoice = Invoice.builder()
                .id(null)
                .date(offsetDateTime)
                .invoiceVoucher(InvoiceVoucher.FACTURA)
                .invoiced(false)
                .paid(false)
                .type(InvoiceType.A)
                .customer(new Customer(1L))
                .company(new Company(1L))
                .total(0).build();
    }

    @Test
    @Order(1)
    void saveTest(){
        Invoice newInvoice = invoiceRepository.save(invoice);

        assertThat(newInvoice).isNotNull();
        assertThat(newInvoice.getId()).isNotNull();

        assertThat(newInvoice.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    void findById(){
        invoiceRepository.save(invoice);

        Invoice invoice1 = invoiceRepository.findById(invoice.getId()).orElse(null);

        assertThat(invoice1).isNotNull();
        assertEquals(invoice1.getId(), invoice.getId());
    }

    @Test
    @Order(Integer.MAX_VALUE)
    void delete(){
        invoiceRepository.save(invoice);

        invoiceRepository.deleteById(invoice.getId());

        Invoice invoice1 = Optional.of(invoiceRepository.findById(invoice.getId())).get().orElse(null);

        assertThat(invoice1).isNull();
    }

}
