package NAR.KittySupplies;

import java.util.*;

/** Toys do not expire and use IDs beginning with T. */
public class Toy extends Product {
    private List<String> colors;

    public Toy(String id, String name, double price, int stock, List<String> colors) {
        super(id, name, price, stock);
        this.colors = colors;
    }
    public List<String> getColors() {
        return colors;
    }
    public void setColors(List<String> colors) {
        this.colors = colors;
    }
    @Override
    public String getType() { return "Toy"; }
    @Override
    public String getExtraDetail() {
        return String.join(",", colors);
    }
    @Override
    public String getDescription() {
        return getExtraDetail();
    }
    @Override
    public void decreaseShelfLife(int days) {
    }
    @Override
    public boolean isExpirable() { return false; }
}
