package uk.co.honestsoftware.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private CurrencyService mockCurrencyService;

    @Test
    public void canGetAllProductsCorrectlyInRequestedCcy() {
        given(mockCurrencyService.getRateFor("USD")).willReturn(1.2);
        given(mockCurrencyService.getRateFor("EUR")).willReturn(1.0);
        final List<Product> products = productController.products("EUR");
        Map<String, Product> before = products.stream().collect(
                Collectors.toMap(x -> x.getName(), x -> x));
        assertThat(before.get("widget").getViewablePrice()).isEqualTo("EUR 0.5");
        final List<Product> all = ProductStore.allProducts("USD", 1.2);
        assertThat(products).containsExactlyInAnyOrder(all.toArray(new Product[all.size()]));
        Map<String, Product> after = all.stream().collect(
                Collectors.toMap(x -> x.getName(), x -> x));
        assertThat(after.get("widget").getViewablePrice()).isEqualTo("USD 0.6");
    }

}
