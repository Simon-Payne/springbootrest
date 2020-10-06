package uk.co.honestsoftware.products;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductControllerTest {

    @Test
    public void canGetAllProductsCorrectlyInDefaultCcy() {
        final List<Product> products = new ProductController().products();
        final List<Product> all = ProductStore.allProducts("USD");
        assertThat(products).containsExactlyInAnyOrder(all.toArray(new Product[all.size()]));
    }

}
