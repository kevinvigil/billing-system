package com.system.billingsystem.controllers;

import com.system.billingsystem.dto.ProductDto;
import com.system.billingsystem.dto.dtosmappers.ProductDtoMapper;
import com.system.billingsystem.entities.Product;
import com.system.billingsystem.entities.microtypes.ids.ProductId;
import com.system.billingsystem.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RestController
public class ProductController {

    private final InvoiceService invoiceService;

    @Autowired
    public ProductController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @PostMapping("/api/product/")
    public ResponseEntity<?> save(@RequestBody ProductDto entity){
        if (entity == null)
            throw new IllegalArgumentException();

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.saveProduct(ProductDtoMapper.toDomain(entity)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/api/product/")
    public ResponseEntity<?> update(@RequestBody ProductDto entity){
        if (entity == null)
            throw new IllegalArgumentException();

        Product product = invoiceService.updateProductById(ProductDtoMapper.toDomain(entity));

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductDtoMapper.toDto(product));
    }

    @DeleteMapping("/api/product/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        Product product = invoiceService.deleteProduct(new ProductId(id));
        return ResponseEntity.ok().body(ProductDtoMapper.toDto(product));
    }

    @GetMapping("/api/product/{id}")
    public  ResponseEntity<?> findById(@PathVariable UUID id){
        try {
            Product product = invoiceService.findProductById(new ProductId(id));
            if (product != null)
                return ResponseEntity.ok().body(ProductDtoMapper.toDto(product));
            return null;
        } catch (Exception e){
            throw new InternalError();
        }
    }

    @GetMapping("/api/product/")
    public ResponseEntity<?> findAll(){
        try {
            List<ProductDto> productDtos = this.invoiceService.findAllProducts().stream().map(ProductDtoMapper::toDto).toList();
            return ResponseEntity.ok().body(productDtos);
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
