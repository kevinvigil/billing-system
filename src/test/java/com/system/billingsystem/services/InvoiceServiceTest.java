package com.system.billingsystem.services;

import com.system.billingsystem.DTOs.*;
import com.system.billingsystem.entities.*;
import com.system.billingsystem.repositories.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

//    @Mock
    @Spy
    private InvoiceRepository invoiceRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private InvoiceProductRepository invoiceProductRepository;

    @Mock
    private CompanyRepository companyRepository;

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
                .id(new UUID(1,1)) .name("Name Company") .cuit("Cuit Company")
                .email("company@hotmail.com") .phone("2150556655") .direction("Company direction")
                .build();

        this.buyerCompany = Company.builder()
                .id(new UUID(1,1)) .name("Name Company") .cuit("Cuit Company")
                .email("company@hotmail.com") .phone("2150556655") .direction("Company direction")
                .build();

        this.product = Product.builder()
                .id(new UUID(1,1)) .name("Name Product") .description("Description Product")
                .price(100.0) .build();

        this.invoiceProduct = InvoiceProduct.builder()
                .id(new UUID(1,1)) .product(null) .invoice(null)
                .build();

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        this.invoice = Invoice.builder()
                .id(new UUID(1,1)) .date(offsetDateTime) .invoiceVoucher(InvoiceVoucher.BILL)
                .invoiced(false) .paid(false) .type(InvoiceType.A) .sellerCompany(this.sellerCompany)
                .buyerCompany(this.buyerCompany) .total(0).build();

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testSaveInvoiceWithZeroProduct() {
        // Configurar el mock del repositorio para que devuelva la factura cuando se llame al método save
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(this.invoice);

        // Llamar al método del servicio
        InvoiceDto invoiceDto = invoiceService.saveInvoice(InvoiceDto.newInvoiceDto(invoice));

        // Verificar que el resultado no sea nulo
        assertNotNull(invoiceDto);

        // Verificar que el resultado sea el esperado
        assertEquals(InvoiceDto.newInvoiceDto(invoice), invoiceDto);

        verify(invoiceRepository, times(1)).save(any(Invoice.class));
    }
}
