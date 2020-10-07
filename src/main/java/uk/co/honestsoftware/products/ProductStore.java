package uk.co.honestsoftware.products;

import javax.money.Monetary;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ProductStore {

    static final String BASE_CCY = "EUR";

    private static final Product WIDGET = new Product(
            1,
            "widget",
            "finely-honed, polished green widget",
            Monetary.getDefaultAmountFactory()
                    .setCurrency(BASE_CCY).setNumber(0.5).create());

    private static final Product SPANNER = new Product(
            2,
            "spanner",
            "superior 13mm open-ended chromium steel spanner",
            Monetary.getDefaultAmountFactory()
                    .setCurrency(BASE_CCY).setNumber(3).create());

    private static final Product GUIDE_BOOK = new Product(
            3,
            "guide-book",
            "extremely comprehensive introduction and guide to all things unrelated to the current business",
            Monetary.getDefaultAmountFactory()
                    .setCurrency(BASE_CCY).setNumber(20).create());

    static final List<Product> allProducts(String viewInCcy, double rateFromBase) {
        return Arrays.asList(WIDGET, SPANNER, GUIDE_BOOK).stream()
                .map(p -> newProduct(p, viewInCcy, rateFromBase))
                .collect(Collectors.toList());
    }

    private static Product newProduct(Product p, String viewCcy, double rate) {
        return new Product(p.getId(), p.getName(), p.getDescription(),
                Monetary.getDefaultAmountFactory()
                        .setCurrency(viewCcy).setNumber(p.getInternalPrice().getNumber().doubleValue() * rate).create());
    }

}
