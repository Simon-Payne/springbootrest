package uk.co.honestsoftware.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CurrencyCodeAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidCurrencyCodeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String currencyCodeNotFoundHandler(InvalidCurrencyCodeException ex) {
        return ex.getMessage();
    }
}