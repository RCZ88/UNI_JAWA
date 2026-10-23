package NAR.reNHlorist;

public class CutFlower extends Product {
    public CutFlower(String productCode, String name, double basePrice, int stock) {
        super(productCode, name, basePrice, stock);
    }

    @Override
    public String getCategory() {
        return "Cut Flower";
    }

    @Override
    public double calculateSellingPrice() {
        return super.calculateSellingPrice();
    }
}
