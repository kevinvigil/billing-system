package com.system.billingsystem.entities.builders.invoicebuilder;

import java.sql.Timestamp;

public interface InvoiceDateStep {
    InvoicePaidStep date(Timestamp date);
}
