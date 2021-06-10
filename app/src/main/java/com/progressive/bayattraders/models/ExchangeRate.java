package com.progressive.bayattraders.models;

public class ExchangeRate {

    String company, rate;

    public ExchangeRate(String company, String rate) {
        this.company = company;
        this.rate = rate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
