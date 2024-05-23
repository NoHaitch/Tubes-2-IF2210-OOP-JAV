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
        /* Insert prices for each product */
        prices = new HashMap<String, Integer>();
        CardConfig config = new CardConfig();
        List<Product> productConfig = config.getProductConfig();
        for(Product product : productConfig){
            prices.put(product.getName(), product.getAddedMoney());
        }
    }
}
