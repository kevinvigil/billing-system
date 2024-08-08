package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class InvoiceRepoTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    private static Invoice invoice;

    private UUID baseId;
    
    @BeforeEach
    public void setUp(){
        invoice = Invoice.builder()
                .date(new Timestamp(1L))
                .invoicevoucher(InvoiceVoucher.BILL)
                .invoiced(false)
                .paid(false)
                .type(InvoiceType.A)
                .buyerCompany(null)
                .sellerCompany(null)
                .total(BigDecimal.valueOf(0)).build();

        baseId = invoiceRepository.save(invoice);
        assertNotNull(baseId);
        invoice.setInvoiceId(baseId);
    }

    @AfterEach
    void tearDown(){
        invoiceRepository.deleteById(baseId);
        Invoice proof = invoiceRepository.findById(baseId);
        assertNull(proof);
    }
    @Test
    public void testFindById(){
        Invoice newInvoice = invoiceRepository.findById(baseId)  ;
        assertNotNull(newInvoice);
        assertEquals(baseId, newInvoice.getInvoiceId());
    }

    @Test
    public void testUpdateInvoice(){
        BigDecimal aux = BigDecimal.valueOf(123456);
        invoice.setTotal(aux);
        invoiceRepository.update(invoice);
        Invoice newInvoice = invoiceRepository.findById(invoice.getInvoiceId())  ;
        assertNotNull(newInvoice);
        assertEquals(aux.doubleValue(), newInvoice.getTotal().doubleValue());
    }

    @Test
    public void testFindAll(){
        Invoice newInvoice = Invoice.builder()
                .date(new Timestamp(2L))
                .invoicevoucher(InvoiceVoucher.REFERENCE)
                .invoiced(true)
                .paid(true)
                .type(InvoiceType.B)
                .buyerCompany(null)
                .sellerCompany(null)
                .total(BigDecimal.valueOf(0)).build();

        assertNotNull(invoiceRepository.save(newInvoice));

        List<Invoice> invoices = invoiceRepository.findAll();
        assertNotNull(invoices);
        assertTrue(invoices.size()>1);
    }

    @Test
    public void testExistById(){
        assertTrue(invoiceRepository.existsById(baseId));
    }
}