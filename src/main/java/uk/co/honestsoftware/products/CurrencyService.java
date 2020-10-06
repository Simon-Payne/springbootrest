package uk.co.honestsoftware.products;

import java.util.Optional;

public interface CurrencyService {

    Optional<Double> getRateFor(String currency);

}
