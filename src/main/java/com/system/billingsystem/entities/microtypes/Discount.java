package com.system.billingsystem.entities.microtypes;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Discount {

    private Integer discount;
    private final Integer MAX = 100;

    public Discount(Integer discount) {
        if (discount < 0 || discount > 100)
            throw new IllegalArgumentException("Invalid discount value");
        this.discount = discount;
    }

    public BigDecimal aplyDiscount(BigDecimal amount){
        return amount.multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(MAX));
    }
}
