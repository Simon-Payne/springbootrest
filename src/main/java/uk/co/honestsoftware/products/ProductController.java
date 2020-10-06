package uk.co.honestsoftware.products;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ProductController {

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/products")
    public List<Product> products(/*@RequestParam(value = "currency", defaultValue = "usd") String currency*/) {
        return ProductStore.allProducts("USD");
    }

}
