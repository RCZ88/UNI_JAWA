package NAR.KittySupplies;

import java.lang.reflect.Field;
import java.util.*;

public abstract class Product {
    protected String id;
    protected String name;
    protected double price;
    protected int stock;

    public Product(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void addStock(int amount) { stock+=amount; }
    public void removeStock(int amount) {
        stock -=amount;
    }

    public abstract String getType();
    public abstract String getExtraDetail();
    public abstract String getDescription();
    public abstract void decreaseShelfLife(int days);
    public abstract boolean isExpirable();


    public String toDataLine() throws IllegalAccessException {
        // Ensure the name cannot contain '#' because it is the field separator.
        StringBuilder sb = new StringBuilder();
        Field[]fields = this.getClass().getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            sb.append(field.get(this)).append("#");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    public void displayDetails() throws IllegalAccessException {
        System.out.println(toDataLine() + getDescription());
    }
}
