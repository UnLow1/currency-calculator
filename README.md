# Currency calculator
You can use this application to count amount from one currency to another. Rates of currencies are automatically taken from outside API (https://free.currencyconverterapi.com) in every 30 min to be actual. You can also check rates of currency in last month. Application also provides an API.
## Getting started
To use application just clone the repo
```$xslt
git clone https://github.com/UnLow1/currency-calculator.git
```
and import it into Eclipse or IntelliJ. 
## Usage with screenshots
*   Home page from where you can navigate through application
<img src="/screenshots/home.PNG" width="550">  
*   First add new currency to take its rate from outside API and save to database
<img src="/screenshots/add_new_currency.PNG" width="550"> 
*   Next you can list added currencies. You can use them in main calculator functionality or to list rates from last month.
<img src="/screenshots/list_of_currencies.PNG" width="550">  
*   Main functionality of calculator, where you can count amount of one currency to another
<img src="/screenshots/calculate.PNG" width="550">  
*   Rates from last moth for currency looks like
<img src="/screenshots/last_month.PNG" width="550">  
*   You can use out API to retrieve data. Use /api/NAME_OF_CURRENCY to get data for currency. For example to take data for USD use /api/USD
<img src="/screenshots/api.PNG" width="550">  
