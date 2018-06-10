package com.springmvc.currencyCalculatorApp.dao;

import com.springmvc.currencyCalculatorApp.entity.CurrencyEntity;
import com.springmvc.currencyCalculatorApp.model.Currency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CurrencyDAOImpl implements CurrencyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean saveCurrency(Currency currency) {
        boolean saveFlag = true;
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setName(currency.getName());
        currencyEntity.setRate(currency.getRate());
        currencyEntity.setLastUpdate(currency.getLastUpdate());

        try {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.saveOrUpdate(currencyEntity);
        } catch (Exception e) {
            e.printStackTrace();
            saveFlag = false;
        }
        return saveFlag;
    }

    @Override
    public List<Currency> getCurrencies() {
        List<Currency> list = new ArrayList<>();

        Session session = sessionFactory.getCurrentSession();
        Query<CurrencyEntity> query = session.createQuery("From CurrencyEntity", CurrencyEntity.class);
        List<CurrencyEntity> currencies = query.getResultList();
        for (CurrencyEntity currencyEntity : currencies) {
            Currency currency = new Currency();
            currency.setName(currencyEntity.getName());
            currency.setRate(currencyEntity.getRate());
            currency.setLastUpdate(currencyEntity.getLastUpdate());

            list.add(currency);
        }

        return list;
    }

    @Override
    public Currency getCurrency(String name) {
        Currency currency = new Currency();
        try {
            Session session = sessionFactory.getCurrentSession();
            CurrencyEntity currencyEntity = session.get(CurrencyEntity.class, name);
            if (currencyEntity == null)
                return null;

            currency.setName(currencyEntity.getName());
            currency.setRate(currencyEntity.getRate());
            currency.setLastUpdate(currencyEntity.getLastUpdate());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return currency;
    }

    @Override
    public void deleteCurrency(String name) {
        try {
            Session session = sessionFactory.getCurrentSession();
            CurrencyEntity currencyEntity = session.load(CurrencyEntity.class, name);
            session.delete(currencyEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Map<String, Double> getLastMonth(Currency currency) throws IOException, JSONException {
        Map<String, Double> lastMonth = currency.getLastMonth();

        return lastMonth;
    }
}
