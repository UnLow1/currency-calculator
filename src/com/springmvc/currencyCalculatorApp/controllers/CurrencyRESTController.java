package com.springmvc.currencyCalculatorApp.controllers;

import com.springmvc.currencyCalculatorApp.model.Currency;
import com.springmvc.currencyCalculatorApp.services.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/currencies")
public class CurrencyRESTController {

    @Autowired
    private CurrencyService currencyService;

    @ResponseBody
    @RequestMapping(value = "/{name}", method=RequestMethod.POST)
    public ResponseEntity<Currency> getCurrency(String name) {
        Currency currency = currencyService.getCurrency(name);
        if (currency == null)
            return new ResponseEntity( HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(currency, HttpStatus.OK);
    }
}
