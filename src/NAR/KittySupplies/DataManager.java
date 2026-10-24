package NAR.KittySupplies;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class DataManager {
    private final Path filePath;

    public DataManager(String fileName) {
        filePath = Path.of(fileName);
    }
    public Store loadStore() throws IOException {
        ArrayList<String>lines = (ArrayList<String>) Files.readAllLines(filePath);
        Store store = new Store(Integer.parseInt(lines.get(0)), Double.parseDouble(lines.get(1)));
        lines.removeFirst(); lines.removeFirst();
        for(String line : lines){
            String []elements = line.split("#");
            char prefix = elements[0].charAt(0);
            Product p = null;
            switch(prefix){
                case 'F':
                    p = new Food(elements[0], elements[1], Double.parseDouble(elements[2]), Integer.parseInt(elements[3]), Integer.parseInt(elements[4]));
                    break;
                case 'M':
                    p = new Medicine(elements[0], elements[1], Double.parseDouble(elements[2]), Integer.parseInt(elements[3]), Integer.parseInt(elements[4]));
                    break;
                case 'T':
                    String[]colors = elements[4].split(",");
                    List<String> strs = new ArrayList<>(Arrays.asList(colors));
                    p = new Toy(elements[0], elements[1], Double.parseDouble(elements[2]), Integer.parseInt(elements[3]), strs);
                    break;
            }
            store.addProduct(p);
        }
        return store;
    }
    public void saveStore(Store store) throws IOException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(store.getDaysPassed()).append("\n");
        sb.append(store.getMoneyBalance()).append("\n");
        for(Product product : store.getProducts()){
            sb.append(product.toDataLine());
            if(product instanceof Toy){
                sb.append(product.getExtraDetail());
            }
            sb.append("\n");
        }
        String[] lines= sb.toString().split("\n");
        Files.write(filePath, Arrays.stream(lines).toList(), StandardOpenOption.APPEND);
    }
}
