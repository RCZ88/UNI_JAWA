package NAR.reNHlorist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalog {
    private final List<Product> products;
    private int nextProductNumber;

    public ProductCatalog() {
        // TODO: Initialize the list and product-code sequence.
        products = new ArrayList<>();
        nextProductNumber = 1000;
    }

    public List<Product> getProducts() {
        // TODO: Return a safe view or copy.
        return Collections.unmodifiableList(products);
    }

    public String generateProductCode() {
        String stringified = String.valueOf(nextProductNumber);
        String code = "K1-1000";
        int toShow = stringified.length()-4;
        code = code.substring(0, toShow) + stringified;
        return code;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product findProductByCode(String productCode) {
        for (Product product : products) {
            if(product.getProductCode().equals(productCode)){
                return product;
            }
        }
        return null;
    }

    public List<EventPackage> getEventPackages() {
        ArrayList<EventPackage>packages = new ArrayList<>();
        for (EventPackage aPackage : packages) {
            if(aPackage instanceof EventPackage){
                packages.add(aPackage);
            }
        }
        return packages;
    }
}
