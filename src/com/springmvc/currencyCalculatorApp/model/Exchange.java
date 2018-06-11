package com.springmvc.currencyCalculatorApp.model;

public class Exchange {

    private String from;
    private String to;
    private double amount;
    private double rate;

    public void countRate(Currency from, Currency to) {
        rate = to.getRate() * amount / from.getRate();
    }

    public Exchange() {
    }

    public Exchange(String from, String to, double amount, double rate) {

        this.from = from;
        this.to = to;
        this.amount = amount;
        this.rate = rate;
    }

    public Exchange(String from, String to, double amount) {

        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
