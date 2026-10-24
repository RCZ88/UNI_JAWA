package NAR.KittySupplies;

/** Food products expire and use IDs beginning with F. */
public class Food extends Product {
    public static final int DEFAULT_SHELF_LIFE = 14;
    private int shelfLife;

    public Food(String id, String name, double price, int stock, int shelfLife) {
        super(id, name, price, stock);
        this.shelfLife = shelfLife;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    @Override public String getType() { return "Food";}
    @Override public String getExtraDetail() {
        return String.valueOf(shelfLife);
    }
    @Override public String getDescription() {
        return String.format("Expires in %s Days", shelfLife);
    }
    @Override public void decreaseShelfLife(int days) {
        shelfLife-=days;
    }
    @Override public boolean isExpirable() { return true; }
    public void refreshAfterOrder(boolean refrigerated) {
        shelfLife = refrigerated ? 19 : DEFAULT_SHELF_LIFE;
    }
}
