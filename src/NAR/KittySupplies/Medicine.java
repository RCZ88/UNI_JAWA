package NAR.KittySupplies;

/** Medicine products expire and use IDs beginning with M. */
public class Medicine extends Product {
    public static final int DEFAULT_SHELF_LIFE = 20;
    private int shelfLife;

    public Medicine(String id, String name, double price, int stock, int shelfLife) {
        super(id, name, price, stock);
        this.shelfLife = shelfLife;
    }
    public int getShelfLife() {  return shelfLife; }
    public void setShelfLife(int days) { this.shelfLife = days;}
    @Override
    public String getType() {
        return "Medicine";
    }
    @Override public String getExtraDetail() {
        return String.valueOf(shelfLife);
    }
    @Override public String getDescription() {
        return String.format("Expires in %s days", shelfLife);
    }
    @Override public void decreaseShelfLife(int days) {
        shelfLife-=days;
    }
    @Override public boolean isExpirable() {  return false; }
    public void refreshAfterOrder() {
        shelfLife = DEFAULT_SHELF_LIFE;
    }
}
