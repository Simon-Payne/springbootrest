package uk.co.honestsoftware.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FixerCurrencyServiceImpl implements CurrencyService {

    @Autowired
    private ProductAppProperties applicationProperties;

    @Override
    public Optional<Double> getRateFor(String currency) {
        if(ProductApplication.fixerData.getRates().containsKey(currency))  {
            return Optional.of(ProductApplication.fixerData.getRates().get(currency));
        }
        return Optional.empty();
    }
}
