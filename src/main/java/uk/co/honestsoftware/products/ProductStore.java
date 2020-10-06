package uk.co.honestsoftware.products;

import javax.money.Monetary;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ProductStore {

    private static final String DEFAULT_CCY = "USD";

    private static final Product WIDGET = new Product(
            1,
            "widget",
            "finely-honed, polished green widget",
            Monetary.getDefaultAmountFactory()
                    .setCurrency(DEFAULT_CCY).setNumber(0.5).create());

    private static final Product SPANNER = new Product(
            2,
            "spanner",
            "superior 13mm open-ended chromium steel spanner",
            Monetary.getDefaultAmountFactory()
                    .setCurrency(DEFAULT_CCY).setNumber(3).create());

    private static final Product GUIDE_BOOK = new Product(
            3,
            "guide-book",
            "extremely comprehensive introduction and guide to all things unrelated to the current business",
            Monetary.getDefaultAmountFactory()
                    .setCurrency(DEFAULT_CCY).setNumber(20).create());

    static final List<Product> allProducts(String viewInCcy) {
        return Arrays.asList(WIDGET, SPANNER, GUIDE_BOOK).stream()
                .map(p -> p.withInternalPrice(viewInCcy))
                .collect(Collectors.toList());
    }

}