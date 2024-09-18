package com.system.billingsystem.controllers;

import com.system.billingsystem.dto.ProductDto;
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

import static com.system.billingsystem.dto.dtosmappers.ProductMapper.PRODUCT_MAPPER;

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
            return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.saveProduct(PRODUCT_MAPPER.toDomain(entity)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/api/product/")
    public ResponseEntity<?> update(@RequestBody ProductDto entity){
        if (entity == null)
            throw new IllegalArgumentException();

        Product product = invoiceService.updateProductById(PRODUCT_MAPPER.toDomain(entity));

        return ResponseEntity.status(HttpStatus.CREATED).body(PRODUCT_MAPPER.toDto(product));
    }

    @DeleteMapping("/api/product/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        Product product = invoiceService.deleteProduct(new ProductId(id));
        return ResponseEntity.ok().body(PRODUCT_MAPPER.toDto(product));
    }

    @GetMapping("/api/product/{id}")
    public  ResponseEntity<?> findById(@PathVariable UUID id){
        try {
            Product product = invoiceService.findProductById(new ProductId(id));
            if (product != null)
                return ResponseEntity.ok().body(PRODUCT_MAPPER.toDto(product));
            return null;
        } catch (Exception e){
            throw new InternalError();
        }
    }

    @GetMapping("/api/product/")
    public ResponseEntity<?> findAll(){
        try {
            List<ProductDto> listProductDto = this.invoiceService.findAllProducts().stream().map(PRODUCT_MAPPER::toDto).toList();
            return ResponseEntity.ok().body(listProductDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
