package NAR.reNHlorist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bouquet extends Product {
    public static final double ARRANGEMENT_FEE = 15_000.0;

    private final List<CutFlower> flowers;

    public Bouquet(String productCode, String name, double basePrice, int stock) {
        super(productCode, name, basePrice, stock);
        flowers = new ArrayList<>();
    }

    public List<CutFlower> getFlowers() {
        return Collections.unmodifiableList(flowers);
    }

    public void addFlower(CutFlower flower) {
        this.flowers.add(flower);
    }

    public boolean removeFlower(CutFlower flower) {
        if(flowers.contains(flower)){
            flowers.remove(flower);
            return true;
        }
        return false;
    }


    @Override
    public String getCategory() {
        return "Bouquet";
    }

    @Override
    public double calculateSellingPrice() {
        double sum = 0;
        for (CutFlower flower : flowers) {
            sum+=flower.getBasePrice();
        }
        return super.calculateSellingPrice() + sum + ARRANGEMENT_FEE;
    }
}
