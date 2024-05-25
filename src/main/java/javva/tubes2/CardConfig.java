package javva.tubes2;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

import javva.tubes2.Card.Product;
import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Plants;
import javva.tubes2.Card.Item;

public class CardConfig {
    private Set<Product> productConfig;
    private Set<Animal> animalConfig;
    private Set<Plants> plantConfig;
    private Set<Item> itemConfig;

    private CardConfig(){
        productConfig = new HashSet<>();

        productConfig.add(new Product("SharkFin", "Meat", "", 12, 500));
        productConfig.add(new Product("Milk", "Meat", "", 4, 100));
        productConfig.add(new Product("LambMeat", "Meat", "", 6, 120));
        productConfig.add(new Product("HorseMeat", "Meat", "", 8, 150));
        productConfig.add(new Product("Egg", "Meat", "", 2, 50));
        productConfig.add(new Product("Bearmeat", "Meat", "", 12, 500));
        productConfig.add(new Product("Corn", "Vegetable", "", 3, 150));
        productConfig.add(new Product("Pumpkin", "Vegetable", "", 10, 500));
        productConfig.add(new Product("Strawberry", "Vegetable", "", 5, 350));

        animalConfig = new HashSet<>();
        animalConfig.add(new Animal("LandShark", "Carnivore", "", new Product("SharkFin", "Product", "", 12, 500), 0, 20));
        animalConfig.add(new Animal("Cow", "Herbivore", "", new Product("Milk", "Product", "", 4, 100), 0, 10));
        animalConfig.add(new Animal("Lamb", "Herbivore", "", new Product("LambMeat", "Product", "", 6, 120), 0, 12));
        animalConfig.add(new Animal("Horse", "Herbivore", "", new Product("HorseMeat", "Product", "", 8, 150), 0, 14));
        animalConfig.add(new Animal("Chicken", "Omnivore", "", new Product("Egg", "Product", "", 2, 50), 0, 5));
        animalConfig.add(new Animal("Bear", "Omnivore", "", new Product("Bearmeat", "Product", "", 12, 500), 0, 25));

        plantConfig = new HashSet<>();
        plantConfig.add(new Plants("CornSeed", "Plant", "", new Product("Corn", "Product", "", 3, 150), 3));
        plantConfig.add(new Plants("PumpkinSeed", "Plant", "", new Product("Pumpkin", "Product", "", 10, 500), 5));
        plantConfig.add(new Plants("StrawberrySeed", "Plant", "", new Product("Strawberry", "Product", "", 5, 350), 4));

        itemConfig = new HashSet<>();
        itemConfig.add(new Item("Accelerate", "Item", ""));
        itemConfig.add(new Item("Delay", "Item", ""));
        itemConfig.add(new Item("InstantHarvest", "Item", ""));
        itemConfig.add(new Item("Destroy", "Item", ""));
        itemConfig.add(new Item("Protect", "Item", ""));
        itemConfig.add(new Item("Trap", "Item", ""));

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

    public Set<Product> getProductConfig() {
        return productConfig;
    }

    public Set<Animal> getAnimalConfig() {
        return animalConfig;
    }

    public Set<Plants> getPlantConfig() {
        return plantConfig;
    }

    public Set<Item> getItemConfig() {
        return itemConfig ;
    }
}
