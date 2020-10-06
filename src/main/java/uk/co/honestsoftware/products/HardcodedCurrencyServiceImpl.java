package uk.co.honestsoftware.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class HardcodedCurrencyServiceImpl implements CurrencyService {

    private final Map<String,Double> ratesMap;

    public HardcodedCurrencyServiceImpl() {
        String path = this.getClass().getResource("/rates.json").getFile();
        try {
            final HashMap hashMap = new ObjectMapper().readValue(new File(path.replaceAll("%20", " ")), HashMap.class);
            ratesMap = (Map<String, Double>) hashMap.get("rates");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Double getRateFor(String currency) {
        return ratesMap.get(currency);
    }


}
