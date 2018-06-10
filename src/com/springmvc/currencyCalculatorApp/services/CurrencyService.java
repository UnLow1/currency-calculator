package com.springmvc.currencyCalculatorApp.services;

import com.springmvc.currencyCalculatorApp.model.Currency;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CurrencyService {

    boolean saveCurrency(Currency currency);

    Map<String, Double> getLastMonth(Currency currency) throws IOException, JSONException;

    List<Currency> getCurrencies();

    Currency getCurrency(String name);

    void deleteCurrency(String name);
}
