package com.system.billingSystem.service;

import com.system.billingSystem.dto.*;
import com.system.billingSystem.model.*;
import com.system.billingSystem.repository.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private InvoiceProductRepository invoiceProductRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    private Invoice invoice;
    private Company sellerCompany;
    private Company buyerCompany;
    private Product product;
    private InvoiceProduct invoiceProduct;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.sellerCompany = Company.builder()
                .id(1L) .name("Name Company") .cuit("Cuit Company")
                .email("company@hotmail.com") .phone("2150556655") .direction("Company direction")
                .build();

        this.buyerCompany = Company.builder()
                .id(1L) .name("Name Company") .cuit("Cuit Company")
                .email("company@hotmail.com") .phone("2150556655") .direction("Company direction")
                .build();


        this.product = Product.builder()
                .id(1L) .name("Name Product") .description("Description Product")
                .price(100.0) .build();

        this.invoiceProduct = InvoiceProduct.builder()
                .id(1L) .product(null) .invoice(null)
                .build();

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        this.invoice = Invoice.builder()
                .id(1L) .date(offsetDateTime) .invoiceVoucher(InvoiceVoucher.BILL)
                .invoiced(false) .paid(false) .type(InvoiceType.A) .sellerCompany(this.sellerCompany)
                .buyerCompany(this.buyerCompany) .total(0).build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testSaveInvoiceWithZeroProduct(){
        mock(Invoice.class);
        mock(InvoiceDto.class);
        mock(InvoiceProductDto.class);
        mock(InvoiceRepository.class);

        assertNotNull(invoice);

        when(invoiceRepository.save(Mockito.any(Invoice.class))).thenReturn(invoice);

        InvoiceDto invoiceDto = invoiceService.saveInvoice(InvoiceDto.newInvoiceDto(invoice));

        assertNotNull(invoiceDto);

        assertEquals(InvoiceDto.newInvoiceDto(invoice), invoiceDto);
    }

//    @Test
//    public void testUpdateInvoiceById(){
//        mock(Invoice.class);
//        mock(InvoiceRepository.class);
//
//        when(invoiceRepository.save(invoice)).thenReturn(invoice);
//
//        InvoiceDto invoiceDto = invoiceService.updateInvoice(InvoiceDto.newInvoiceDto(invoice));
//
//        assertNotNull(invoiceDto);
//
//        assertEquals(invoice, Invoice.newInvoice(invoiceDto));
//    }
//
//    @Test
//    public void testUpdateInvoice() {
//        // Given
//        Long invoiceId = 1L;
//        Product product = new Product(1L, "Product Name", "Product Description", 100.0);
//
//        InvoiceProductDto invoiceProductDto = new InvoiceProductDto(null, "Product Name", 2.0, product.getId(), invoiceId);
//
//        List<InvoiceProductDto> productList = new ArrayList<>();
//        productList.add(invoiceProductDto);
//
//        InvoiceDto existingInvoiceDto = new InvoiceDto(invoiceId, OffsetDateTime.now(), false, false, 0.0, InvoiceVoucher.BILL.name(), InvoiceType.A.name(), 1L, 1L, productList);
//
//        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(new Invoice()));
//        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
//        doNothing().when(invoiceRepository).deleteById(invoiceId);
//        when(invoiceRepository.save(any(Invoice.class))).thenReturn(new Invoice());
//
//        // When
//        InvoiceDto updatedInvoiceDto = invoiceService.updateInvoice(existingInvoiceDto);
//
//        // Then
//        assertNotNull(updatedInvoiceDto);
//        verify(invoiceRepository, times(1)).deleteById(invoiceId);
//        verify(invoiceRepository, times(1)).save(any(Invoice.class));
//    }
//
//    @Test
//    public void testDeleteInvoice() {
//        // Given
//        invoiceRepository.save(invoice);
//        Long invoiceId = invoice.getId();
//
//        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(new Invoice()));
//        doNothing().when(invoiceRepository).deleteById(invoiceId);
//
//        // When
//        InvoiceDto deletedInvoiceDto = invoiceService.deleteInvoice(invoiceId);
//
//        // Then
//        assertNotNull(deletedInvoiceDto);
//        assertEquals(invoiceId, deletedInvoiceDto.id());
//        verify(invoiceRepository, times(1)).findById(invoiceId);
//        verify(invoiceRepository, times(1)).deleteById(invoiceId);
//    }
//
//
//    @Test
//    void testFindByValidId (){
//        // Given
//        Long validId = invoice.getId();
//        when(invoiceRepository.findById(validId)).thenReturn(Optional.of(invoice));
//
//        // When
//        InvoiceDto result = invoiceService.findInvoiceById(validId);
//
//        // Then
//        assertNotNull(result);
//        assertEquals(validId, result.id());
//        verify(invoiceRepository, times(1)).findById(validId);
//    }
//
//    @Test
//    void testFindByInvalidId (){
//        // Given
//        Long invalidId = 2L;
//        when(invoiceRepository.findById(invalidId)).thenReturn(Optional.empty());
//
//        // When
//        InvoiceDto result = invoiceService.findInvoiceById(invalidId);
//
//        // Then
//        assertNull(result);
//        verify(invoiceRepository, times(1)).findById(invalidId);
//    }
//
//    @Test
//    void testFindByIdWithException(){
//        // Given
//        Long id = 3L;
//        when(invoiceRepository.findById(id)).thenThrow(new RuntimeException("Error on data base"));
//
//        // When
//        RuntimeException e = assertThrows(RuntimeException.class, () -> {
//            invoiceRepository.findById(id);
//        });
//
//        // Then
//        assertEquals("Error on data base", e);
//        verify(invoiceRepository, times(1)).findById(id);
//    }

}
