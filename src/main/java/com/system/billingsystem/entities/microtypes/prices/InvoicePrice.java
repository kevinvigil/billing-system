package com.system.billingsystem.entities.microtypes.prices;

import java.math.BigDecimal;

public class InvoicePrice extends BasePrice{

    public InvoicePrice(Currency currency, BigDecimal price) {
        super(currency, price);
    }

    public InvoicePrice(BigDecimal price) {
        super(price);
    }
}
