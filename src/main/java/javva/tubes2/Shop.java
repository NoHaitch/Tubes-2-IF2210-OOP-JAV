package javva.tubes2;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import javva.tubes2.Card.Product;
import javva.tubes2.CardConfig;

public class Shop {
    /* ATTRIBUTE */
    private Map<String, Integer> products;
    private Map<String, Integer> prices;

    /* CONSTRUCTOR */
    private Shop(){
        CardConfig config = CardConfig.getInstance();
        List<Product> productConfig = config.getProductConfig().stream().toList();
        products = new HashMap<String, Integer>();
        for (Product p : productConfig){
            products.put(p.getName(), 0);
        }
        createPricesMap(productConfig);
    }

    /* Singleton Pattern */
    private static Shop instance = null;

    /* Singleton Instance Getter */
    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    /* METHODS */
    public void addProduct(String productName){
        products.put(productName, products.get(productName) + 1);
    }

    public void removeProduct(String productName) throws NotRemovableProduct{
        if (isSoldOut(productName)){
            throw new NotRemovableProduct();
        } else {
            products.put(productName, products.get(productName) - 1);
        }
    }

    public Integer getQuantity(String productName){
        return products.get(productName);
    }

    public boolean isSoldOut(String productName){
        return products.get(productName) == 0;
    }

    public List<Map.Entry<String, Integer>> getShopItemsList(){
        return new ArrayList<>(products.entrySet());
    }

    public Integer getPrice(String productName){
        return prices.get(productName);
    }

    public void createPricesMap(List<Product> productConfig){
        /* Insert prices for each product */
        prices = new HashMap<String, Integer>();
        for(Product product : productConfig){
            prices.put(product.getName(), product.getAddedMoney());
        }
    }

    public class NotRemovableProduct extends Exception{
        NotRemovableProduct(){super("No item to be removed");}
    }
}
