package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.InvoiceProduct;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                .invoiceProduct_id(new UUID(1,1))
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
        InvoiceProduct foundInvoiceProduct = invoiceProductRepo.findById(newInvoiceProduct.getInvoiceProduct_id()) ;
        assertNotNull(foundInvoiceProduct);
        assertEquals(newInvoiceProduct.getInvoiceProduct_id(), foundInvoiceProduct.getInvoiceProduct_id());
    }

    @Test
    public void testFindInvoiceProductById(){
        InvoiceProduct newInvoiceProduct = invoiceProductRepo.save(invoiceProduct);
        assertNotNull(newInvoiceProduct);
        InvoiceProduct foundInvoiceProduct = invoiceProductRepo.findById(newInvoiceProduct.getInvoiceProduct_id()) ;
        assertNotNull(foundInvoiceProduct);
        assertEquals(newInvoiceProduct.getInvoiceProduct_id(), foundInvoiceProduct.getInvoiceProduct_id());
    }

    @Test
    public void testDeleteInvoiceProduct(){
        InvoiceProduct newInvoiceProduct = invoiceProductRepo.save(invoiceProduct);
        assertNotNull(newInvoiceProduct);
        InvoiceProduct foundInvoiceProduct = invoiceProductRepo.findById(newInvoiceProduct.getInvoiceProduct_id()) ;
        assertNotNull(foundInvoiceProduct);
        assertEquals(newInvoiceProduct.getInvoiceProduct_id(), foundInvoiceProduct.getInvoiceProduct_id());
        invoiceProductRepo.deleteById(newInvoiceProduct.getInvoiceProduct_id());
        foundInvoiceProduct = invoiceProductRepo.findById(newInvoiceProduct.getInvoiceProduct_id()) ;
        assertNull(foundInvoiceProduct);
    }

    @Test
    public void testFindAllInvoiceProduct(){
        invoiceProductRepo.save(invoiceProduct);
        InvoiceProduct newInvoiceProduct = InvoiceProduct.builder()
                .invoiceProduct_id(new UUID(2,2))
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
                .invoiceProduct_id(new UUID(2,2))
                .amount(200.0)
                .product(null)
                .invoice(null)
                .build();

        invoiceProductRepo.save(newInvoiceProduct);
        invoiceProductRepo.save(invoiceProduct);

        List<InvoiceProduct> invoiceProducts = invoiceProductRepo.findAll();
        assertNotNull(invoiceProducts);
        assertEquals(2, invoiceProducts.size());
    }
}
