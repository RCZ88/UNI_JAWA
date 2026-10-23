package NAR.reNHlorist;

public class PottedPlant extends Product {
    public static final double POT_FEE = 17_000.0;

    public PottedPlant(String productCode, String name, double basePrice, int stock) {
        super(productCode, name, basePrice, stock);
    }

    @Override
    public String getCategory() {
        return "PottedPlant";
    }

    @Override
    public double calculateSellingPrice() {
        return calculateSellingPrice() + POT_FEE;
    }
}
