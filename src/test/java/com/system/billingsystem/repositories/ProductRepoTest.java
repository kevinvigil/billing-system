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
import java.util.UUID;

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
                .product_id(new UUID(1,1))
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
        Product foundProduct = productRepo.findById(savedProduct.getProduct_id())   ;
        assertNotNull(foundProduct);
        assertEquals(savedProduct.getProduct_id(), foundProduct.getProduct_id());
    }

    @Test
    public void testUpdateProduct(){
        Product savedProduct = productRepo.save(product);
        assertNotNull(savedProduct);
        savedProduct.setName("Updated Name");
        productRepo.save(savedProduct);
        Product updatedProduct = productRepo.findById(savedProduct.getProduct_id())   ;
        assertNotNull(updatedProduct);
        assertEquals(updatedProduct.getName(), savedProduct.getName());
    }

    @Test
    public void testDeleteProduct(){
        Product savedProduct = productRepo.save(product);
        assertNotNull(savedProduct);
        Product foundProduct = productRepo.findById(savedProduct.getProduct_id())   ;
        assertNotNull(foundProduct);
        assertEquals(savedProduct.getProduct_id(), foundProduct.getProduct_id());
        productRepo.deleteById(savedProduct.getProduct_id());
        assertNull(productRepo.findById(savedProduct.getProduct_id()));
    }

    @Test
    public void testFindAllProducts(){
        Product savedProduct = productRepo.save(product);
        assertNotNull(savedProduct);
        Product newProduct = Product.builder()
                .product_id(new UUID(2,2))
                .name("Name Product 2")
                .description("Description Product 2")
                .price(200.0)
                .build();
        productRepo.save(newProduct);
        List<Product> products = productRepo.findAll();
        assertNotNull(products);
        assertEquals(2, products.size());
    }
}
