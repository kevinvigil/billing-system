package com.system.billingsystem.controllers;

import com.system.billingsystem.dto.InvoiceDto;
import com.system.billingsystem.entities.Invoice;
import com.system.billingsystem.entities.microtypes.ids.InvoiceId;
import com.system.billingsystem.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.system.billingsystem.dto.dtosmappers.InvoiceMapper.INVOICE_MAPPER;

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

        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.saveInvoice(INVOICE_MAPPER.toDomain(entity)));
    }

    @PutMapping("/api/invoice/")
    public ResponseEntity<?> update(@RequestBody InvoiceDto entity) {
        if (entity == null)
            throw new IllegalArgumentException();

        try {
            Invoice invoice = invoiceService.findInvoiceById(new InvoiceId(entity.invoiceId()));

            if (!invoice.isInvoiced() && !invoice.isPaid())
                return ResponseEntity.ok().body(invoiceService.updateInvoice(INVOICE_MAPPER.toDomain(entity)));
            else if (!invoice.isInvoiced()) {
                Invoice domain = INVOICE_MAPPER.toDomain(entity);
                if (Objects.equals(invoice, domain) && invoice.isInvoiced() != entity.invoiced())
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
        InvoiceDto invoiceDto = INVOICE_MAPPER.toDto(invoiceService.deleteInvoice(new InvoiceId(id)));
        return ResponseEntity.ok().body(invoiceDto);
    }

    @GetMapping("/api/invoice/{id}")
    public  ResponseEntity<?> findById(@PathVariable UUID id){
        try{
            Invoice invoice = invoiceService.findInvoiceById(new InvoiceId(id));
            if (invoice == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.ok().body(INVOICE_MAPPER.toDto(invoice));
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
                List<InvoiceDto> invoiceDto = invoices.stream().map(INVOICE_MAPPER::toDto).toList();
                return ResponseEntity.ok().body(invoiceDto);
            }
            return null;
        } catch (Exception e){
            throw new InternalError();
        }
    }
}
