package com.system.billingsystem.entities.microtypes.prices;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

@Data
public class BasePrice implements Comparable<BasePrice> {
    private Currency currency;
    private BigDecimal value;

    private static HashMap<Currency, HashMap<Currency, BigDecimal>> basePrices = new HashMap<>();

    public BasePrice(Currency currency, BigDecimal value) {
        this.currency = Objects.requireNonNullElse(currency, Currency.ARS);
        this.value = value;
    }

    public BasePrice(BigDecimal value) {
        this.value = value;
        this.currency = Currency.ARS;
    }

    public BigDecimal plus(BigDecimal  amount, Currency currency){
        if (currency.name().compareTo(this.currency.name()) == 0)
            this.value = this.value.add(amount);
        else
            this.value = this.value.add(currencyExchange(amount, currency, this.currency));

        return this.value;
    }

    @Override
    public int compareTo(@NotNull BasePrice o) {
        if (o.currency.name().compareTo(this.currency.name()) == 0)
            return o.value.compareTo(this.value);

        return currencyExchange(this.value, this.currency, o.currency).compareTo(o.value);
    }

    public static BigDecimal currencyExchange(BigDecimal amount, Currency baseCurrency, Currency newCurrency) {
        HashMap<Currency, BigDecimal> curr = basePrices.get(newCurrency);
        if (curr == null || curr.isEmpty())
            return BigDecimal.ONE;

        BigDecimal value = curr.get(baseCurrency);

        if (value == null)
            return BigDecimal.ONE;

        return amount.multiply(value);
    }
}
