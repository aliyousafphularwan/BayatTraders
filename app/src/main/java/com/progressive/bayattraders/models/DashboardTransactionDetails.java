package com.progressive.bayattraders.models;

public class DashboardTransactionDetails {

    String name, amount, pin, date;

    public DashboardTransactionDetails(String name, String amount, String pin, String date) {
        this.name = name;
        this.amount = amount;
        this.pin = pin;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
