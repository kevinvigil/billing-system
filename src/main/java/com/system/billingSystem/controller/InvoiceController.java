package com.system.billingSystem.controller;

import com.system.billingSystem.dto.InvoiceDto;
import com.system.billingSystem.exeption.BadRequestException;
import com.system.billingSystem.repository.InvoiceRepository;
import com.system.billingSystem.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, InvoiceRepository invoiceRepository){
        this.invoiceRepository = invoiceRepository;
        this.invoiceService = invoiceService;
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody InvoiceDto entity){
        if (entity == null)
            throw new BadRequestException("Invalid invoice data");

        InvoiceDto invoiceDto = invoiceService.saveInvoice(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDto);

    }

    // TODO
//    @PutMapping("")
//    public ResponseEntity<?> update(@RequestBody InvoiceDto entity) {
//        try {
//            InvoiceDto invoiceDto = invoiceService.findInvoiceById(entity.getId());
//            if (invoiceDto != null && !invoiceDto.isInvoiced() && invoiceService.updateInvoiceById(entity)){
//                if (!entity.isPaid()){
//                    return ResponseEntity.ok().body(HttpStatus.OK);
//                }
//
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("this invoice can not be changed");
//            }
//        } catch (Exception e){
//            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500");
//        }
//
//        return null;
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        InvoiceDto invoiceDto = invoiceService.deleteInvoice(id);
        return ResponseEntity.ok().body(invoiceDto);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> findById(@PathVariable Long id){
        try{
            InvoiceDto invoiceDto = invoiceService.findInvoiceById(id);
            return ResponseEntity.ok().body(invoiceDto);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bad request");
        }
    }
}
