package NAR.KittySupplies;

import java.util.*;
import java.util.stream.Collectors;

/** In-memory state and business rules. Main handles console prompts; DataManager handles files. */
public class Store {
    private final List<Product> products = new ArrayList<>();
    private int daysPassed;
    private double moneyBalance;
    private HashMap<Character, Integer>idCount = new HashMap<>();
    private static final int ID_MAX_LEN = 5;

    public Store(int daysPassed, double moneyBalance) {
        this.daysPassed = daysPassed;
        this.moneyBalance = moneyBalance;
    }
    public List<Product> getProducts() {
        // TODO: Return a safe view/copy of the product list.
        return null;
    }
    public int getDaysPassed() { /* TODO: Return completed days. */ return 0; }
    public double getMoneyBalance() { /* TODO: Return current balance. */ return 0; }
    public void setProducts(List<Product> loaded) {
        // TODO: Replace current list with products loaded from data.txt.
    }
    public Product findProductById(String id) {
        Product p = null;
        for (Product product : products) {
            if(product.getId().equals(id)){
                return p;
            }
        }
        return null;

    }
    public String generateNextProductId(String type) {
        char key = type.toUpperCase().charAt(0);
        int count = idCount.getOrDefault(key, 0);
        if(count == 0){
            idCount.put(key, 0);
        }else{
            idCount.put(key, idCount.get(key)+1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for(int i = 0; i<ID_MAX_LEN-String.valueOf(count).length(); i++){
            sb.append(0);
        }
        sb.append(count);
        return sb.toString();
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }
    public boolean updateProduct(String id, String name, double price, List<String> colors) {
        Product p = findProductById(id);
        if(p == null)return false;
        p.setName(name);
        p.setPrice(price);
        if(p instanceof Toy){
            ((Toy) p).getColors().addAll(colors);
        }

        return true;
    }
    public boolean deleteProduct(String id) {
        Product toRemove = findProductById(id);
        if(toRemove == null) return false;
        products.remove(toRemove);
        return true;
    }
    public double calculateOrderBaseTotal(Product product, int amount) {
        return product.getPrice() * 0.5 *amount;
    }
    public boolean orderFood(String id, int amount) {


        return false;
    }
    public String orderMedicine(String id, int amount, String tier) {
        Product product = findProductById(id);
        if(product == null)return null;
        double cost = calculateOrderBaseTotal(product, amount);
        boolean spoiled = false;
        int chance = 0;
        switch (tier){
            case "VIP":
                cost *=2;
                break;
            case "PREMIUM":
                cost *=1.5;
                chance = 20;
                break;
            case "STANDARD":
                chance = 30;
                break;
        }
        spoiled = new Random().nextInt(1, 100) < chance ;

        return  "SOMETHING";
    }
    public boolean orderToy(String id, int amount) {
        if(amount >= 1 && amount <= 20){
            Product p = findProductById(id);
            /*product.removeStock(toBuy);
                moneyBalance += product.price * toBuy;*/
            p.removeStock(amount);
            moneyBalance+= p.price * amount;
            return true;
        }else{
            return false;
        }
    }
    public void startNewDay(Random random) {
        products.forEach(p->{
            if(p instanceof Food || p instanceof Medicine){
                p.decreaseShelfLife(1);
                if(p instanceof Food){
                    if(((Food)p).getShelfLife() <= 0){
                        p.setStock(0);
                    }else if(random.nextInt(1, 100) < 15){
                        p.setStock((int)(p.getStock() * 0.8));
                    }
                }else{
                    if(random.nextInt(1, 100) < 20){
                        p.decreaseShelfLife(2);
                    }
                    if(((Medicine)p).getShelfLife() <=0){
                        p.setStock(0);
                    }
                }
            }else if(p instanceof Toy){
                if(random.nextInt(1, 100) < 30 && p.getStock() > 0){
                    p.removeStock(1);
                }
            }
        });
        daysPassed++;
        int itemsBought = 0;
        ArrayList<Integer>itemBoughtIndex = new ArrayList<>();
        if(products.size() < 5) return;
        while(itemsBought < 5){
            int itemIndex = random.nextInt(0, products.size()-1);
            if(itemBoughtIndex.contains(itemIndex))continue;
            Product product = products.get(itemIndex);
            int toBuy = (int) Math.max(1, Math.floor(0.8 * product.stock));
            if(product.stock >= toBuy){
                if (!orderToy(product.getId(), toBuy)) continue;
                switch(product.getType()){
                    case "Medicine":
                        orderMedicine(product.getId(), toBuy, "VIP");
                        break;
                    case "Toy":
                        orderToy(product.getId(), toBuy);
                        break;
                    case "Food":
                        orderFood(product.getId(), toBuy);
                }
                itemBoughtIndex.add(itemIndex);
                itemsBought++;
            }
        }


    }
    public void displayAllProducts() {
        for (Product product : products) {
            System.out.printf("%s(%s): stock: %d, %s ", product.getName(), product.getType(), product.getStock(), product.getExtraDetail());
        }
    }
    public void displayFoodProducts() {
        ArrayList<Product>foods = products.stream().filter(p -> p.getType().equals("Food")).sorted(Comparator.comparingInt(m -> ((Food) m).getShelfLife())).collect(Collectors.toCollection(ArrayList::new));
        for (Product food : foods) {
            System.out.println(food.toString());
        }
    }
    public void displayMedicineProducts() {
        ArrayList<Product>medicines = products.stream().filter(p -> p.getType().equals("Medicine")).sorted(Comparator.comparingInt(m -> ((Medicine) m).getShelfLife())).collect(Collectors.toCollection(ArrayList::new));
        for(Product medicine : medicines){
            System.out.println(medicine.toString());
        }
    }
    public void displayToyProducts() {
        ArrayList<Product> toys = products.stream().filter(p -> p.getType().equals("Toy")).sorted(Comparator.comparing(Product::getId)).collect(Collectors.toCollection(ArrayList::new));
        for (Product toy : toys) {
            System.out.println(toy.toString());
        }
    }
}
