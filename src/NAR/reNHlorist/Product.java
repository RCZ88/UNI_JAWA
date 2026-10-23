package NAR.reNHlorist;

import java.util.HashMap;
import java.util.Map;

public abstract class Product {
    public static final double TAX_RATE = 0.10;

    private final String productCode;
    private String name;
    private double basePrice;
    private int stock;

    public static int totalProductsCreated = 0;
    public static final Map<String, Integer> stockByCategory = new HashMap<>();

    public Product(String productCode, String name, double basePrice, int stock) {
        this.productCode = productCode;
        this.name = name;
        this.basePrice = basePrice;
        this.stock = stock;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public abstract String getCategory();

    public double calculateSellingPrice() {
        return basePrice * (TAX_RATE + 1);
    }

    protected void updateCategoryStock(int difference) {
        this.stock += difference;
    }
}
