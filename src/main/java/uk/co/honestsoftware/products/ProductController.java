package uk.co.honestsoftware.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private final CurrencyService currencyService;

    @Autowired
    public ProductController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping("/products")
    public List<Product> products(@RequestParam(value = "currency", defaultValue = "EUR") String currency) {
        return ProductStore.allProducts("EUR", conversionRate("EUR"));
    }

    private double conversionRate(String currency) {
        return currencyService.getRateFor(currency);
    }

}
