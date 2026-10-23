package NAR.reNHlorist;

public class FuneralArrangement extends Bouquet {
    public static final double FUNERAL_FEE = 42_000.0;

    public FuneralArrangement(String productCode, String name, double basePrice, int stock) {
        super(productCode, name, basePrice, stock);
    }

    @Override
    public String getCategory() {
        return "Funeral Arrangement";
    }

    @Override
    public final double calculateSellingPrice() {
        return super.calculateSellingPrice() + FUNERAL_FEE;
    }
}
