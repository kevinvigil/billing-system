package com.system.billingsystem.entities.microtypes.prices;

import com.system.billingsystem.entities.Currency;

import java.math.BigDecimal;

public class ProductPrice extends BasePrice{
    public ProductPrice(Currency currency, BigDecimal price) {
        super(currency, price);
    }

    public ProductPrice(BigDecimal price) {
        super(price);
    }
}
