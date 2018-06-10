package com.springmvc.currencyCalculatorApp.dao;

import com.springmvc.currencyCalculatorApp.model.Currency;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CurrencyDAO {

    boolean saveCurrency(Currency currency);

    List<Currency> getCurrencies();

    Currency getCurrency(String name);

    void deleteCurrency(String name);

    Map<String,Double> getLastMonth(Currency currency) throws IOException, JSONException;
}
