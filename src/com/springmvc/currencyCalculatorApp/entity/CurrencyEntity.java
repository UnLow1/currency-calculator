package com.springmvc.currencyCalculatorApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "rates")
public class CurrencyEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "rate")
    private double rate;

    @Column(name = "last_update")
    private Date lastUpdate;

    public CurrencyEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
