package com.system.billingsystem.dto.dtosmappers;

import com.system.billingsystem.dto.CompanyDto;
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

        CompanyDto sellerCompany = null;
        CompanyDto buyerCompany = null;

        if (invoice.getSellerCompany() != null)
            sellerCompany = CompanyDtoMapper.toDto(invoice.getSellerCompany());

        if (invoice.getBuyerCompany() != null)
            buyerCompany = CompanyDtoMapper.toDto(invoice.getBuyerCompany());

        return new InvoiceDto(
                invoice.getInvoiceId(),
                invoice.getDate(),
                invoice.isPaid(),
                invoice.isInvoiced(),
                invoice.getTotal(),
                (invoice.getInvoicevoucher() == null)? null:invoice.getInvoicevoucher().name(),
                (invoice.getCategory() == null)? null : invoice.getCategory().name(),
                sellerCompany,
                buyerCompany,
                products
        );
    }

    public static Invoice toDomain(InvoiceDto invoiceDto) {
        Invoice invoice = new Invoice();

        invoice.setInvoiceId(invoiceDto.invoiceId());
        invoice.setPaid(invoiceDto.paid());
        invoice.setInvoiced(invoiceDto.invoiced());
        invoice.setTotal(invoiceDto.total());
        invoice.setInvoicevoucher(InvoiceVoucher.valueOf(invoiceDto.invoiceVoucher()));
        invoice.setCategory(InvoiceCategory.valueOf(invoiceDto.type()));
        invoice.setSellerCompany(CompanyDtoMapper.toDomain(invoiceDto.sellerCompany()));
        invoice.setBuyerCompany(CompanyDtoMapper.toDomain(invoiceDto.buyerCompany()));

        return invoice;
    }
}
