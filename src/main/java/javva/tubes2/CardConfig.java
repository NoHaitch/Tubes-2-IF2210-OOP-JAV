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
        productConfig.addLast(new Product("SharkFin", "Meat", "/javva/tubes2/images/Produk/shark-fin.png", 12, 500));
        productConfig.addLast(new Product("Milk", "Meat", "/javva/tubes2/images/Produk/susu.png", 4, 100));
        productConfig.addLast(new Product("LambMeat", "Meat", "/javva/tubes2/images/Produk/Daging Domba.png", 6, 120));
        productConfig.addLast(new Product("HorseMeat", "Meat", "/javva/tubes2/images/Produk/Daging Kuda.png", 8, 150));
        productConfig.addLast(new Product("Egg", "Meat", "/javva/tubes2/images/Produk/telur.png", 2, 50));
        productConfig.addLast(new Product("Bearmeat", "Meat", "/javva/tubes2/images/Produk/Daging Beruang.png", 12, 500));
        productConfig.addLast(new Product("Corn", "Vegetable", "/javva/tubes2/images/Produk/corn.png", 3, 150));
        productConfig.addLast(new Product("Pumpkin", "Vegetable", "/javva/tubes2/images/Produk/pumpkin.png", 10, 500));
        productConfig.addLast(new Product("Strawberry", "Vegetable", "/javva/tubes2/images/Produk/strawberry.png", 5, 350));

        animalConfig = new ArrayList<>();
        animalConfig.addLast(new Animal("LandShark", "Carnivore", "/javva/tubes2/images/Hewan/hiu_darat.png", new Product("SharkFin", "Product", "", 12, 500), 0, 20));
        animalConfig.addLast(new Animal("Cow", "Herbivore", "/javva/tubes2/images/Hewan/cow.png", new Product("Milk", "Product", "", 4, 100), 0, 10));
        animalConfig.addLast(new Animal("Lamb", "Herbivore", "/javva/tubes2/images/Hewan/sheep.png", new Product("LambMeat", "Product", "", 6, 120), 0, 12));
        animalConfig.addLast(new Animal("Horse", "Herbivore", "/javva/tubes2/images/Hewan/horse.png", new Product("HorseMeat", "Product", "", 8, 150), 0, 14));
        animalConfig.addLast(new Animal("Chicken", "Omnivore", "/javva/tubes2/images/Hewan/chicken.png", new Product("Egg", "Product", "", 2, 50), 0, 5));
        animalConfig.addLast(new Animal("Bear", "Omnivore", "/javva/tubes2/images/Hewan/bear.png", new Product("Bearmeat", "Product", "", 12, 500), 0, 25));

        plantConfig = new ArrayList<>();
        plantConfig.addLast(new Plants("CornSeed", "Plant", "/javva/tubes2/images/Tanaman/corn seeds.png", new Product("Corn", "Product", "", 3, 150), 3));
        plantConfig.addLast(new Plants("PumpkinSeed", "Plant", "/javva/tubes2/images/Tanaman/pumpkin seeds.png", new Product("Pumpkin", "Product", "", 10, 500), 5));
        plantConfig.addLast(new Plants("StrawberrySeed", "Plant", "/javva/tubes2/images/Tanaman/strawberry seed.png", new Product("Strawberry", "Product", "", 5, 350), 4));

        itemConfig = new ArrayList<>() ;
        itemConfig.addLast(new Item("Accelerate", "Item", "/javva/tubes2/images/Item/Accelerate.png"));
        itemConfig.addLast(new Item("Delay", "Item", "/javva/tubes2/images/Item/Delay.png"));
        itemConfig.addLast(new Item("InstantHarvest", "Item", "/javva/tubes2/images/Item/Instant Harvest.png"));
        itemConfig.addLast(new Item("Destroy", "Item", "/javva/tubes2/images/Item/Destroy.png"));
        itemConfig.addLast(new Item("Protect", "Item", "/javva/tubes2/images/Item/Protect.png"));
        itemConfig.addLast(new Item("Trap", "Item", "/javva/tubes2/images/Item/bear trap.png"));

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
