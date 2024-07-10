package com.system.billingSystem.repository;

import com.system.billingSystem.model.InvoiceProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class InvoiceProductRepoTest {

    @Autowired
    private InvoiceProductRepository invoiceProductRepo;

    private static InvoiceProduct invoiceProduct;

    @BeforeAll
    public static void setUp(){
        invoiceProduct = InvoiceProduct.builder()
                .id(1L)
                .product(null)
                .invoice(null)
                .build();
    }

    @AfterEach
    public void tearDown(){
        invoiceProductRepo.deleteAll();
    }

    @Test
    public void testSaveInvoiceProduct(){
        InvoiceProduct newInvoiceProduct = invoiceProductRepo.save(invoiceProduct);
        assertNotNull(newInvoiceProduct);
        InvoiceProduct foundInvoiceProduct = invoiceProductRepo.findById(newInvoiceProduct.getId()).orElse(null);
        assertNotNull(foundInvoiceProduct);
        assertEquals(newInvoiceProduct.getId(), foundInvoiceProduct.getId());
    }

    @Test
    public void testFindInvoiceProductById(){
        InvoiceProduct newInvoiceProduct = invoiceProductRepo.save(invoiceProduct);
        assertNotNull(newInvoiceProduct);
        InvoiceProduct foundInvoiceProduct = invoiceProductRepo.findById(newInvoiceProduct.getId()).orElse(null);
        assertNotNull(foundInvoiceProduct);
        assertEquals(newInvoiceProduct.getId(), foundInvoiceProduct.getId());
    }

    @Test
    public void testDeleteInvoiceProduct(){
        InvoiceProduct newInvoiceProduct = invoiceProductRepo.save(invoiceProduct);
        assertNotNull(newInvoiceProduct);
        InvoiceProduct foundInvoiceProduct = invoiceProductRepo.findById(newInvoiceProduct.getId()).orElse(null);
        assertNotNull(foundInvoiceProduct);
        assertEquals(newInvoiceProduct.getId(), foundInvoiceProduct.getId());
        invoiceProductRepo.deleteById(newInvoiceProduct.getId());
        foundInvoiceProduct = invoiceProductRepo.findById(newInvoiceProduct.getId()).orElse(null);
        assertNull(foundInvoiceProduct);
    }

    @Test
    public void testFindAllInvoiceProduct(){
        invoiceProductRepo.save(invoiceProduct);
        InvoiceProduct newInvoiceProduct = InvoiceProduct.builder()
                .id(3L)
                .product(null)
                .invoice(null)
                .build();

        invoiceProductRepo.save(newInvoiceProduct);

        List<InvoiceProduct> invoiceProducts = invoiceProductRepo.findAll();
        assertNotNull(invoiceProducts);
        assertEquals(2, invoiceProducts.size());
    }

    @Test
    public void testSaveAllInvoiceProduct(){
        InvoiceProduct newInvoiceProduct = InvoiceProduct.builder()
                .id(2L)
                .amount(200.0)
                .product(null)
                .invoice(null)
                .build();

        List<InvoiceProduct> invoiceProductList = new ArrayList<>();
        invoiceProductList.add(invoiceProduct);
        invoiceProductList.add(newInvoiceProduct);
        invoiceProductRepo.saveAll(invoiceProductList);
        List<InvoiceProduct> invoiceProducts = invoiceProductRepo.findAll();
        assertNotNull(invoiceProducts);
        assertEquals(invoiceProductList.size(), invoiceProducts.size());
    }
}
