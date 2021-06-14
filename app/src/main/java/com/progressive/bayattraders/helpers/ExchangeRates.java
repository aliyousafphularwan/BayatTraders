package com.progressive.bayattraders.helpers;

public class ExchangeRates {

    public String name, rate;

    public ExchangeRates(String name, String rate) {
        this.name = name;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return name;
    }
}
