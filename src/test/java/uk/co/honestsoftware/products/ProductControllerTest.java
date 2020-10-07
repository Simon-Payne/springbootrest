package uk.co.honestsoftware.products;

import org.assertj.core.api.ThrowableAssertAlternative;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private CurrencyService mockCurrencyService;

    @Test
    public void canGetAllProductsCorrectlyInRequestedCcy() {
        given(mockCurrencyService.getRateFor("USD")).willReturn(Optional.of(1.2));
        given(mockCurrencyService.getRateFor("EUR")).willReturn(Optional.of(1.0));
        final List<Product> products = productController.products("EUR");
        Map<String, Product> before = products.stream().collect(
                Collectors.toMap(x -> x.getName(), x -> x));
        assertThat(before.get("widget").getViewablePrice()).isEqualTo("EUR 0.5");
        final List<Product> all = ProductStore.allProducts("USD", mockCurrencyService.getRateFor("USD").get());
        assertThat(all.size()).isEqualTo(3);
        Map<String, Product> after = all.stream().collect(
                Collectors.toMap(x -> x.getName(), x -> x));
        assertThat(after.get("widget").getViewablePrice()).isEqualTo("USD 0.6");
    }

    @Test
    public void canHandleUnrecognisedCurrency() {
        assertThat(mockCurrencyService.getRateFor("ABC")).isEmpty();
    }

    @Test
    public void canGetProductsinUSDThenEURCorrectly() {
        given(mockCurrencyService.getRateFor("USD")).willReturn(Optional.of(1.2));
        given(mockCurrencyService.getRateFor("EUR")).willReturn(Optional.of(1.0));
        final Map<String, Product> dollarMap = ProductStore.allProducts("USD", mockCurrencyService.getRateFor("USD").get()).stream().collect(
                Collectors.toMap(x -> x.getName(), x -> x));
        final Map<String, Product> euroMap = ProductStore.allProducts("EUR", mockCurrencyService.getRateFor("EUR").get()).stream().collect(
                Collectors.toMap(x -> x.getName(), x -> x));
        assertThat(dollarMap.get("widget").getViewablePrice()).isEqualTo("USD 0.6");
        assertThat(euroMap.get("widget").getViewablePrice()).isEqualTo("EUR 0.5");
    }

}
