package uk.co.honestsoftware.products;

public class InvalidCurrencyCodeException extends RuntimeException {


    private final String currency;

    public InvalidCurrencyCodeException(String currency) {
        this.currency = currency;
    }

    @Override
    public String getMessage() {
        return String.format("Unrecognised currency code: " + currency);
    }

}
