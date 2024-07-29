package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.InvoiceDto;
import com.system.billingsystem.dto.InvoiceProductDto;
import com.system.billingsystem.entities.*;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDtoMapper {

    private InvoiceDtoMapper() {}

    public static InvoiceDto toDto(Invoice invoice) {
        List<InvoiceProduct> productList = invoice.getProducts();
        List <InvoiceProductDto> products = new ArrayList<>();

        if (productList != null && !productList.isEmpty()){
            for (InvoiceProduct ip:productList) {
                products.add(InvoiceProductDtoMapper.toDto(ip));
            }
        }

        return new InvoiceDto(
                invoice.getInvoice_id(), invoice.getDate(), invoice.isPaid(), invoice.isInvoiced(),
                invoice.getTotal(), invoice.getInvoiceVoucher().name(), invoice.getType().name(),
                invoice.getSellerCompany().getCompany_id(), invoice.getBuyerCompany().getCompany_id(),
                products
        );
    }

    public static Invoice toDomain(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();

        invoice.setInvoice_id(invoiceDto.invoiceDto_id());
        invoice.setPaid(invoiceDto.paid());
        invoice.setInvoiced(invoiceDto.invoiced());
        invoice.setTotal(invoiceDto.total());
        invoice.setInvoiceVoucher(InvoiceVoucher.valueOf(invoiceDto.invoiceVoucher()));
        invoice.setType(InvoiceType.valueOf(invoiceDto.type()));
        invoice.setSellerCompany(new Company(invoiceDto.sellerCompany()));
        invoice.setBuyerCompany(new Company(invoiceDto.buyerCompany()));


        return invoice;
    }
}
