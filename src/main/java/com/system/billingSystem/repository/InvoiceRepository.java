package com.system.billingSystem.repository;

import com.system.billingSystem.model.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository("InvoiceRepository")
public interface InvoiceRepository extends BaseRepository<Invoice, Long> {

    @Modifying
    @Transactional
    @Query("update Invoice i set i.date = :date, i.paid = :paid, i.invoiced=:invoiced, i.total=:total, " +
            "i.invoiceVoucher=:invoiceVoucher, i.type = :type, i.company = :company, i.customer = :customer " +
            "where i.id = :id")
    void updateInvoiceById(@Param("id") Long id,
                           @Param("date") OffsetDateTime date,
                           @Param("paid") boolean paid,
                           @Param("invoiced") boolean invoiced,
                           @Param("total") double total,
                           @Param("invoiceVoucher") InvoiceVoucher invoiceVoucher,
                           @Param("type") InvoiceType type,
                           @Param("company") Company company,
                           @Param("customer") Customer customer);
    
//    @Transactional
//    @Query("select * " +
//            "from invoice i left join company c1_0 on c1_0.id=i.company_id " +
//            "    left join customer c2_0 on c2_0.id=i.customer_id " +
//            "    left join invoice_product p1_0 on i.invoice_id=p1_0.invoice_id " +
//            "    left join product p2_0 on p2_0.product_id=p1_0.product_id " +
//            "where i.invoice_id=?")
//    Optional<Invoice> findById(@Param("id") Long id);
}
