package com.system.billingsystem.controllers;

import com.system.billingsystem.dto.InvoiceDto;
import com.system.billingsystem.dto.dtosmappers.InvoiceDtoMapper;
import com.system.billingsystem.entities.Invoice;
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
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @PostMapping("/api/invoice/")
    public ResponseEntity<?> save(@RequestBody InvoiceDto entity){
        if (entity == null)
            throw new IllegalArgumentException();

        Invoice invoice = invoiceService.saveInvoice(InvoiceDtoMapper.toDomain(entity));
        return ResponseEntity.status(HttpStatus.CREATED).body(InvoiceDtoMapper.toDto(invoice));
    }

    @PutMapping("/api/invoice/")
    public ResponseEntity<?> update(@RequestBody InvoiceDto entity) {
        if (entity == null)
            throw new IllegalArgumentException();

        try {
            Invoice invoice = invoiceService.findInvoiceById(entity.invoiceDtoId());

            if (!invoice.isInvoiced() && !invoice.isPaid())
                return ResponseEntity.ok().body(invoiceService.updateInvoice(InvoiceDtoMapper.toDomain(entity)));
            else if (!invoice.isInvoiced()) {
                Invoice domain = InvoiceDtoMapper.toDomain(entity);
                if ( invoice.equals(domain) && invoice.isInvoiced() != entity.invoiced())
                    return ResponseEntity.ok().body(invoiceService.updateInvoice(domain));
                else
                    throw new UnsupportedOperationException("This invoice can change only to be invoiced" +
                            " because it is already paid");
            } else
                throw new UnsupportedOperationException("This invoice can not be changed, " +
                        "because it is already invoiced");

        } catch (Exception e){
            throw new InternalError();
        }
    }

    @DeleteMapping("/api/invoice/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        InvoiceDto invoiceDto = InvoiceDtoMapper.toDto(invoiceService.deleteInvoice(id));
        return ResponseEntity.ok().body(invoiceDto);
    }

    @GetMapping("/api/invoice/{id}")
    public  ResponseEntity<?> findById(@PathVariable UUID id){
        try{
            InvoiceDto invoiceDto = InvoiceDtoMapper.toDto(invoiceService.findInvoiceById(id)) ;
            return ResponseEntity.ok().body(invoiceDto);
        } catch (Exception e){
            throw new InternalError();
        }
    }

    @GetMapping("/api/invoice/")
    public  ResponseEntity<?> findAll(){
        try{
            List<Invoice> invoices = this.invoiceService.findAllInvoices();
            System.out.println(invoices);
            if (invoices != null){
                List<InvoiceDto> invoiceDto = invoices.stream().map(InvoiceDtoMapper::toDto).toList();
                return ResponseEntity.ok().body(invoiceDto);
            }
            return null;
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
