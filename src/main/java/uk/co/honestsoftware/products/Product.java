package uk.co.honestsoftware.products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import static uk.co.honestsoftware.products.ProductStore.BASE_CCY;

@JsonIgnoreProperties(value = { "internalPrice" })
public class Product {

    private final long id;
    private final String name;
    private final String description;
    private MonetaryAmount internalPrice;
    private String viewablePrice;

    public Product(long id, String name, String description, MonetaryAmount internalPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.internalPrice = internalPrice;
        this.viewablePrice = internalPrice.toString();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public MonetaryAmount getInternalPrice() {
        return internalPrice;
    }

    @JsonProperty("price")
    public String getViewablePrice() {
        return viewablePrice;
    }

    Product withInternalPrice(String viewInCcy, double rateFromBase) {
        if(!viewInCcy.equals(BASE_CCY)) {
            final double newNumber = internalPrice.multiply(rateFromBase).getNumber().doubleValueExact();
            this.internalPrice = Monetary.getDefaultAmountFactory()
                    .setCurrency(viewInCcy).setNumber(newNumber).create();
            this.viewablePrice = internalPrice.toString();
        }
        return this;
    }

}
