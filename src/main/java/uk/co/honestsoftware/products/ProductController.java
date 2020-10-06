package uk.co.honestsoftware.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {


    @Autowired
    private final CurrencyService currencyService;

    @Autowired
    public ProductController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping("/products")
    public List<Product> products(@RequestParam(value = "currency", defaultValue = "EUR") String currency) {
        final double rate = conversionRate(currency);
        return ProductStore.allProducts(currency, rate);
    }

    private double conversionRate(String currency) {
        return currencyService.getRateFor(currency).orElseThrow(() -> new InvalidCurrencyCodeException(currency));
    }

}
