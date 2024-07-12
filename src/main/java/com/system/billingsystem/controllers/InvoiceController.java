package com.system.billingsystem.controllers;

import com.system.billingsystem.DTOs.InvoiceDto;
import com.system.billingsystem.repositories.InvoiceRepository;
import com.system.billingsystem.services.InvoiceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
            throw new IllegalArgumentException();

        InvoiceDto invoiceDto = invoiceService.saveInvoice(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceDto);
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody InvoiceDto entity) {
        if (entity == null)
            throw new IllegalArgumentException();

        try {
            InvoiceDto invoiceDto = invoiceService.findInvoiceById(entity.id());
            if (invoiceDto != null){
                if (!invoiceDto.invoiced() && !invoiceDto.paid())
                    return ResponseEntity.ok().body(invoiceService.updateInvoice(entity));
                else if (!invoiceDto.invoiced()){
                    if (InvoiceDto.compareInvoices(invoiceDto, entity) && invoiceDto.invoiced() != entity.invoiced())
                        return ResponseEntity.ok().body(invoiceService.updateInvoice(entity));
                    else
                        throw new UnsupportedOperationException("This invoice can change only to be invoiced" +
                                " because it is already paid");
                }
                else
                    throw new UnsupportedOperationException("This invoice can not be changed, " +
                            "because it is already invoiced");
            }
            else
                throw new EntityNotFoundException();
        } catch (Exception e){
            throw new InternalError();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        InvoiceDto invoiceDto = invoiceService.deleteInvoice(id);
        return ResponseEntity.ok().body(invoiceDto);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> findById(@PathVariable UUID id){
        try{
            InvoiceDto invoiceDto = invoiceService.findInvoiceById(id);
            if (invoiceDto != null)
                return ResponseEntity.ok().body(invoiceDto);
            else
                throw new EntityNotFoundException();
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
