package com.springmvc.currencyCalculatorApp.services;

import com.springmvc.currencyCalculatorApp.dao.CurrencyDAO;
import com.springmvc.currencyCalculatorApp.model.Currency;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyDAO currencyDAO;

    @Override
    @Transactional
    public boolean saveCurrency(Currency currency) {
        return currencyDAO.saveCurrency(currency);
    }

    @Override
    @Transactional
    public Map<String, Double> getLastMonth(Currency currency) throws IOException, JSONException {
        return currencyDAO.getLastMonth(currency);
    }

    @Override
    @Transactional
    public List<Currency> getCurrencies() {
        return currencyDAO.getCurrencies();
    }

    @Override
    @Transactional
    public Currency getCurrency(String name) {
        return currencyDAO.getCurrency(name);
    }

    @Override
    @Transactional
    public void deleteCurrency(String name) {
        currencyDAO.deleteCurrency(name);
    }
}
