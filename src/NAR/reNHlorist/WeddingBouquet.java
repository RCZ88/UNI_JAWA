package NAR.reNHlorist;

public class WeddingBouquet extends Bouquet {
    private double decorationFee;

    public WeddingBouquet(String productCode, String name, double basePrice, int stock, double decorationFee) {
        super(productCode, name, basePrice, stock);
        this.decorationFee = decorationFee;
    }

    public double getDecorationFee() {
        return decorationFee;
    }

    public void setDecorationFee(double decorationFee) {
        this.decorationFee = decorationFee;
    }

    @Override
    public String getCategory() {
        return "Wedding Bouquet";
    }

    @Override
    public double calculateSellingPrice() {
        return super.calculateSellingPrice() + decorationFee;
    }
}
