package javva.tubes2;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    /* ATTRIBUTE */
    private Map<String, Integer> products;
    private Map<String, Integer> prices;

    /* CONSTRUCTOR */
    public Shop(){
        products = new HashMap<String, Integer>();
        createPricesMap();
    }

    /* METHODS */
    public void addProduct(String productName){
        products.merge(productName, 1, Integer::sum);
    }

    public void removeProduct(String productName){
        products.put(productName, products.get(productName)-1);
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

    public void createPricesMap(){
        //TODO: insert prices for each product
    }
}
