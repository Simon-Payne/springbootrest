package uk.co.honestsoftware.products;

import org.junit.jupiter.api.Test;

import javax.money.Monetary;

import static org.assertj.core.api.Assertions.*;

public class ProductTest {

    @Test
    public void initialisesCorrectly() {
        final Product product = new Product(1, "test", "test description", Monetary.getDefaultAmountFactory()
                .setCurrency("GBP").setNumber(20).create());
        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getName()).isEqualTo("test");
        assertThat(product.getDescription()).isEqualTo("test description");
        assertThat(product.getViewablePrice()).isEqualTo("GBP 20");
    }

    @Test
    public void appliesRequestedPriceCorrectly() {
        final Product product = new Product(1, "test", "test description", Monetary.getDefaultAmountFactory()
                .setCurrency("GBP").setNumber(20).create()).withInternalPrice("USD");
        assertThat(product.getId()).isEqualTo(1);
        assertThat(product.getName()).isEqualTo("test");
        assertThat(product.getDescription()).isEqualTo("test description");
        assertThat(product.getViewablePrice()).isEqualTo("USD 20");
    }

}
