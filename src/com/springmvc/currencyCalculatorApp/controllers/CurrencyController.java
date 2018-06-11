package com.springmvc.currencyCalculatorApp.controllers;

import com.springmvc.currencyCalculatorApp.model.Currency;
import com.springmvc.currencyCalculatorApp.model.Exchange;
import com.springmvc.currencyCalculatorApp.services.CurrencyService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class CurrencyController {

    public static final int MINUTE = 60 * 1000;


    @Autowired
    CurrencyService currencyService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor ste = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, ste);
    }

    @RequestMapping("/")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("/new")
    public String newCurrency(Model model) {

        model.addAttribute("currency", new Currency());

        return "currency-form";
    }

    @RequestMapping("/calculate")
    public String calculate(Model model) {

        model.addAttribute("exchange", new Exchange());

        return "calculate";
    }

    @RequestMapping(value = "/calculateRates", method = RequestMethod.POST)
    public String calculateRates(Model model, Exchange exchange) {

        String message = "";
        String result;
        boolean flag = true;

        try {
            Currency from = currencyService.getCurrency(exchange.getFrom());
            Currency to = currencyService.getCurrency(exchange.getTo());

            validateLastUpdateDate(from);
            validateLastUpdateDate(to);

            exchange.countRate(from, to);

            result = String.valueOf(exchange.getRate());
            model.addAttribute("result", result);

        } catch (Exception ex) {
            message = ex.getMessage();
            flag = false;
        }

        if (!flag)
            model.addAttribute("message", message);

        return "calculate";
    }

    private void validateLastUpdateDate(Currency currency) throws IOException, JSONException {
        if (currency == null)
            throw new RuntimeException("Currency does not exist in database. You have to add it in '\\new' page");

        Date now = new Date();

        // check if last update is older than 30 minutes
        Date lastUpdatePlus30min = new Date(currency.getLastUpdate().getTime() + 30 * MINUTE);

        if (lastUpdatePlus30min.before(now)) {
            currency.setRate();
            currency.setLastUpdate(now);
            currencyService.saveCurrency(currency);
        }
    }

    @RequestMapping(value = "/saveCurrency", method = RequestMethod.POST)
    public String saveCurrency(@Valid @ModelAttribute("currency") Currency currency,
                               BindingResult result, Model model) {

        if (result.hasErrors())
            return "currency-form";

        String message = "";
        boolean flag;

        try {
            currency.setRate();
            currency.setLastUpdate(new Date());

            flag = currencyService.saveCurrency(currency);
        } catch (Exception ex) {
            message = ex.getMessage();
            flag = false;
        }
        if (!flag) {
            model.addAttribute("message", message);
            return "currency-form";
        }

        model.addAttribute("currency", currency);
        return "redirect:/list";

    }

    @GetMapping("/list")
    public String listCurrencies(Model model) {
        List<Currency> currencies = currencyService.getCurrencies();
        model.addAttribute("currencies", currencies);
        return "listCurrencies";
    }

    @GetMapping("/update")
    public String updateCurrency(@RequestParam("name") String name) {
        Currency currency = currencyService.getCurrency(name);
        try {
            currency.setRate();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        currency.setLastUpdate(new Date());

        currencyService.saveCurrency(currency);
        return "redirect:/list";
    }

    @GetMapping
    public String history(@RequestParam("name") String name, Model model) {
        Currency currency = currencyService.getCurrency(name);

        try {
            model.addAttribute("history", currency.getLastMonth());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return "history";
    }

    @GetMapping("/delete")
    public String deleteCurrency(@RequestParam("name") String name) {
        currencyService.deleteCurrency(name);
        return "redirect:/list";
    }

    @ResponseBody
    @RequestMapping(value = "/api/{id}", method = RequestMethod.GET)
    public Currency getCurrency(@PathVariable("id") String name) {
        return currencyService.getCurrency(name);
    }

    @RequestMapping("*")
    public String pageNotFound() {
        return "fileNotFound";
    }
}
