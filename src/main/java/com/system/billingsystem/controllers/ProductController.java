package com.system.billingsystem.controllers;

import com.system.billingsystem.dto.dtosmappers.ProductDtoMapper;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.repositories.ProductRepository;
import com.system.billingsystem.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final InvoiceService invoiceService;

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(InvoiceService invoiceService, ProductRepository productRepository){
        this.invoiceService = invoiceService;
        this.productRepository = productRepository;
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Product entity){
        if (entity == null)
            throw new IllegalArgumentException();

        boolean isNew = entity.getProduct_id() == null || !productRepository.existsById(entity.getProduct_id());

        Product product = invoiceService.saveProduct(entity);

        if (isNew) {
            return ResponseEntity.status(HttpStatus.CREATED).body(ProductDtoMapper.toDto(product));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ProductDtoMapper.toDto(product));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        Product product = invoiceService.deleteProduct(id);
        return ResponseEntity.ok().body(ProductDtoMapper.toDto(product));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> findById(@PathVariable UUID id){
        try {
            Product product = invoiceService.findProductById(id);
            if (product != null)
                return ResponseEntity.ok().body(product);
            else
                throw new Exception();// TODO
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
