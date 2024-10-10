package com.system.billingsystem.entities.microtypes;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
public class Discount {

    private Integer discount;
    private static final Integer MAX = 100;

    public Discount(Integer discount) {
        if (discount < 0 || discount > 100)
            throw new IllegalArgumentException("Invalid discount value");
        this.discount = discount;
    }

    public BigDecimal aplyDiscount(BigDecimal amount){
        return amount.multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(MAX));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(discount, discount.discount);
    }
}
