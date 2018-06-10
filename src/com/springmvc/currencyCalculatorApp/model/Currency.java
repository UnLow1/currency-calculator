package com.springmvc.currencyCalculatorApp.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;


public class Currency {

    final static String BASE_CURRENCY = "PLN";
    final static String BASE_URL = "https://free.currencyconverterapi.com/api/v5/convert?q=" + BASE_CURRENCY + "_";
    final static String COMPACT = "&compact=ultra";
    final static long DAY = 60 * 60 * 24 * 1000;

    // TODO delete this 2 lines?
    @Size(min = 3, max = 3)
    @NotNull
    @Pattern(regexp = "[A-Z]+")
    private String name;

    private double rate;

    private Date lastUpdate;


    public Currency(String name, Float rate, Date lastUpdate) {
        this.name = name;
        this.rate = rate;
        this.lastUpdate = lastUpdate;
    }

    public Currency() {

    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
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

    public Map<String, Double> getLastMonth() throws IOException, JSONException {

        Map<String, Double> result = new LinkedHashMap<>();

        JSONObject response;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date now = new Date();

        Date startDate = new Date(now.getTime() - 30 * DAY);
        String startDateFormatted = formatter.format(startDate.getTime());

        // because in free.currencyconverterapi.com only getting max 8 days are available
        Date endDate = new Date(startDate.getTime() + 8 * DAY);
        String endDateFormatted = formatter.format(endDate.getTime());

        while (now.after(startDate)) {
            response = readResponseFromApi(BASE_URL + name + COMPACT + "&date=" + startDateFormatted + "&endDate=" + endDateFormatted);

            if (response.length() == 0)
                throw new RuntimeException("Currency " + name + " is not available");

            JSONObject history = response.getJSONObject(BASE_CURRENCY + "_" + name);

            LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
                Date date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
                if (!now.after(date1))
                    break;
                double rate = history.getDouble(String.valueOf(date));
                result.put(date.toString(), rate);
            }

            startDate = endDate;
            startDateFormatted = endDateFormatted;

            endDate = new Date(endDate.getTime() + 8 * DAY);
            endDateFormatted = formatter.format(endDate.getTime());

        }

        return result;
    }

    public void setRate() throws JSONException, IOException {

        JSONObject response;
        response = readResponseFromApi(BASE_URL + name + COMPACT);

        if (response.length() == 0)
            throw new RuntimeException("Currency " + name + " is not available");

        rate = response.getDouble(BASE_CURRENCY + "_" + name);

    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    private JSONObject readResponseFromApi(String url) throws IOException, JSONException {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);
        in.close();

        return new JSONObject(response.toString());
    }
}
