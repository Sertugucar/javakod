package tr.kafemenu.model;

import java.math.BigDecimal;

/** Menüdeki bir ürünü temsil eder. */
public class Product {
    private final int id;
    private final String name;
    private final String category;
    private final String description;
    private final BigDecimal price;

    public Product(int id, String name, String category, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
