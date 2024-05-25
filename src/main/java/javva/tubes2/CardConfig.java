package javva.tubes2;

import java.util.List;
import java.util.ArrayList;

import javva.tubes2.Card.Product;
import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Plants;
import javva.tubes2.Card.Item;

public class CardConfig {
    private List<Product> productConfig;
    private List<Animal> animalConfig;
    private List<Plants> plantConfig;
    private List<Item> itemConfig ;

    private CardConfig(){
        productConfig = new ArrayList<>();
        productConfig.addLast(new Product("SharkFin", "Meat", "", 12, 500));
        productConfig.addLast(new Product("Milk", "Meat", "", 4, 100));
        productConfig.addLast(new Product("LambMeat", "Meat", "", 6, 120));
        productConfig.addLast(new Product("HorseMeat", "Meat", "", 8, 150));
        productConfig.addLast(new Product("Egg", "Meat", "", 2, 50));
        productConfig.addLast(new Product("Bearmeat", "Meat", "", 12, 500));
        productConfig.addLast(new Product("Corn", "Vegetable", "", 3, 150));
        productConfig.addLast(new Product("Pumpkin", "Vegetable", "", 10, 500));
        productConfig.addLast(new Product("Strawberry", "Vegetable", "", 5, 350));

        animalConfig = new ArrayList<>();
        animalConfig.addLast(new Animal("LandShark", "Carnivore", "/javva/tubes2/images/Hewan/hiu_darat.png", new Product("SharkFin", "Product", "", 12, 500), 0, 20));
        animalConfig.addLast(new Animal("Cow", "Herbivore", "/javva/tubes2/images/Hewan/cow.png", new Product("Milk", "Product", "", 4, 100), 0, 10));
        animalConfig.addLast(new Animal("Lamb", "Herbivore", "", new Product("LambMeat", "Product", "", 6, 120), 0, 12));
        animalConfig.addLast(new Animal("Horse", "Herbivore", "", new Product("HorseMeat", "Product", "", 8, 150), 0, 14));
        animalConfig.addLast(new Animal("Chicken", "Omnivore", "", new Product("Egg", "Product", "", 2, 50), 0, 5));
        animalConfig.addLast(new Animal("Bear", "Omnivore", "", new Product("Bearmeat", "Product", "", 12, 500), 0, 25));

        plantConfig = new ArrayList<>();
        plantConfig.addLast(new Plants("CornSeed", "Plant", "", new Product("Corn", "Product", "", 3, 150), 3));
        plantConfig.addLast(new Plants("PumpkinSeed", "Plant", "", new Product("Pumpkin", "Product", "", 10, 500), 5));
        plantConfig.addLast(new Plants("StrawberrySeed", "Plant", "", new Product("Strawberry", "Product", "", 5, 350), 4));

        itemConfig = new ArrayList<>() ;
        itemConfig.addLast(new Item("Accelerate", "Item", ""));
        itemConfig.addLast(new Item("Delay", "Item", ""));
        itemConfig.addLast(new Item("InstantHarvest", "Item", ""));
        itemConfig.addLast(new Item("Destroy", "Item", ""));
        itemConfig.addLast(new Item("Protect", "Item", ""));
        itemConfig.addLast(new Item("Trap", "Item", ""));

    }

    /* Singleton Pattern */
    private static CardConfig instance = null;

    /* Singleton Instance Getter */
    public static CardConfig getInstance() {
        if (instance == null) {
            instance = new CardConfig();
        }
        return instance;
    }

    public List<Product> getProductConfig(){
        return productConfig;
    }

    public List<Animal> getAnimalConfig(){
        return animalConfig;
    }

    public List<Plants> getPlantConfig(){
        return plantConfig;
    }

    public List<Item> getItemConfig() {
        return itemConfig ;
    }
}
