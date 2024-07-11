package com.system.billingsystem.repositories;

import com.system.billingsystem.entities.Product;
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
public class ProductRepoTest {

    @Autowired
    private ProductRepository productRepo;

    private static Product product;

    @BeforeAll
    public static void setUp(){
        product = Product.builder()
                .id(1L)
                .name("Name Product")
                .description("Description Product")
                .price(100.0)
                .build();
    }

    @AfterEach
    public void tearDown(){
        productRepo.deleteAll();
    }

    @Test
    public void testSaveProduct(){
        Product savedProduct = productRepo.save(product);
        assertNotNull(savedProduct);
        assertEquals(savedProduct.getName(), product.getName());
    }

    @Test
    public void testFindProductById(){
        Product savedProduct = productRepo.save(product);
        assertNotNull(savedProduct);
        Product foundProduct = productRepo.findById(savedProduct.getId()).orElse(null);
        assertNotNull(foundProduct);
        assertEquals(savedProduct.getId(), foundProduct.getId());
    }

    @Test
    public void testUpdateProduct(){
        Product savedProduct = productRepo.save(product);
        assertNotNull(savedProduct);
        savedProduct.setName("Updated Name");
        productRepo.save(savedProduct);
        Product updatedProduct = productRepo.findById(savedProduct.getId()).orElse(null);
        assertNotNull(updatedProduct);
        assertEquals(updatedProduct.getName(), savedProduct.getName());
    }

    @Test
    public void testDeleteProduct(){
        Product savedProduct = productRepo.save(product);
        assertNotNull(savedProduct);
        Product foundProduct = productRepo.findById(savedProduct.getId()).orElse(null);
        assertNotNull(foundProduct);
        assertEquals(savedProduct.getId(), foundProduct.getId());
        productRepo.deleteById(savedProduct.getId());
        assertNull(productRepo.findById(savedProduct.getId()).orElse(null));
    }

    @Test
    public void testFindAllProducts(){
        Product savedProduct = productRepo.save(product);
        assertNotNull(savedProduct);
        Product newProduct = Product.builder()
                .id(2L)
                .name("Name Product 2")
                .description("Description Product 2")
                .price(200.0)
                .build();
        productRepo.save(newProduct);
        List<Product> products = productRepo.findAll();
        assertNotNull(products);
        assertEquals(2, products.size());
    }

    @Test
    public void testSaveAllProducts(){
        Product newProduct = Product.builder()
                .id(2L)
                .name("Name Product 2")
                .description("Description Product 2")
                .price(200.0)
                .build();

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(newProduct);
        productRepo.saveAll(products);
        List<Product> productsFounded = productRepo.findAll();
        assertNotNull(productsFounded);
        assertEquals(2, productsFounded.size());
    }
}
