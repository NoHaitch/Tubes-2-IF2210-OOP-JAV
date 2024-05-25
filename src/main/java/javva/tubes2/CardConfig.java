package javva.tubes2;

import java.util.HashSet;
import java.util.Set;

import javva.tubes2.Card.Product;
import javva.tubes2.Card.Animal;
import javva.tubes2.Card.Plants;
import javva.tubes2.Card.Item;

public class CardConfig {
    private Set<Product> productConfig;
    private Set<Animal> animalConfig;
    private Set<Plants> plantConfig;
    private Set<Item> itemConfig ;

    private CardConfig(){
        productConfig = new HashSet<>();
        productConfig.add(new Product("SharkFin", "Meat", "/javva/tubes2/images/Produk/shark-fin.png", 12, 500));
        productConfig.add(new Product("Milk", "Meat", "/javva/tubes2/images/Produk/susu.png", 4, 100));
        productConfig.add(new Product("LambMeat", "Meat", "/javva/tubes2/images/Produk/Daging Domba.png", 6, 120));
        productConfig.add(new Product("HorseMeat", "Meat", "/javva/tubes2/images/Produk/Daging Kuda.png", 8, 150));
        productConfig.add(new Product("Egg", "Meat", "/javva/tubes2/images/Produk/telur.png", 2, 50));
        productConfig.add(new Product("Bearmeat", "Meat", "/javva/tubes2/images/Produk/Daging Beruang.png", 12, 500));
        productConfig.add(new Product("Corn", "Vegetable", "/javva/tubes2/images/Produk/corn.png", 3, 150));
        productConfig.add(new Product("Pumpkin", "Vegetable", "/javva/tubes2/images/Produk/pumpkin.png", 10, 500));
        productConfig.add(new Product("Strawberry", "Vegetable", "/javva/tubes2/images/Produk/strawberry.png", 5, 350));

        animalConfig = new HashSet<>();
        animalConfig.add(new Animal("LandShark", "Carnivore", "/javva/tubes2/images/Hewan/hiu_darat.png", new Product("SharkFin", "Meat", "/javva/tubes2/images/Produk/shark-fin.png", 12, 500), 0, 20));
        animalConfig.add(new Animal("Cow", "Herbivore", "/javva/tubes2/images/Hewan/cow.png", new Product("Milk", "Meat", "/javva/tubes2/images/Produk/susu.png", 4, 100), 0, 10));
        animalConfig.add(new Animal("Lamb", "Herbivore", "/javva/tubes2/images/Hewan/sheep.png", new Product("LambMeat", "Meat", "/javva/tubes2/images/Produk/Daging Domba.png", 6, 120), 0, 12));
        animalConfig.add(new Animal("Horse", "Herbivore", "/javva/tubes2/images/Hewan/horse.png", new Product("HorseMeat", "Meat", "/javva/tubes2/images/Produk/Daging Kuda.png", 8, 150), 0, 14));
        animalConfig.add(new Animal("Chicken", "Omnivore", "/javva/tubes2/images/Hewan/chicken.png", new Product("Egg", "Meat", "/javva/tubes2/images/Produk/telur.png", 2, 50), 0, 5));
        animalConfig.add(new Animal("Bear", "Omnivore", "/javva/tubes2/images/Hewan/bear.png", new Product("Bearmeat", "Meat", "/javva/tubes2/images/Produk/Daging Beruang.png", 12, 500), 0, 25));

        plantConfig = new HashSet<>();
        plantConfig.add(new Plants("CornSeed", "Plant", "/javva/tubes2/images/Tanaman/corn seeds.png", new Product("Corn", "Vegetable", "/javva/tubes2/images/Produk/corn.png", 3, 150), 3));
        plantConfig.add(new Plants("PumpkinSeed", "Plant", "/javva/tubes2/images/Tanaman/pumpkin seeds.png", new Product("Pumpkin", "Vegetable", "/javva/tubes2/images/Produk/pumpkin.png", 10, 500), 5));
        plantConfig.add(new Plants("StrawberrySeed", "Plant", "/javva/tubes2/images/Tanaman/strawberry seeds.png", new Product("Strawberry", "Vegetable", "/javva/tubes2/images/Produk/strawberry.png", 5, 350), 4));

        itemConfig = new HashSet<>() ;
        itemConfig.add(new Item("Accelerate", "Item", "/javva/tubes2/images/Item/Accelerate.png"));
        itemConfig.add(new Item("Delay", "Item", "/javva/tubes2/images/Item/Delay.png"));
        itemConfig.add(new Item("InstantHarvest", "Item", "/javva/tubes2/images/Item/Instant Harvest.png"));
        itemConfig.add(new Item("Destroy", "Item", "/javva/tubes2/images/Item/Destroy.png"));
        itemConfig.add(new Item("Protect", "Item", "/javva/tubes2/images/Item/Protect.png"));
        itemConfig.add(new Item("Trap", "Item", "/javva/tubes2/images/Item/bear trap.png"));

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

    public Set<Product> getProductConfig(){
        return productConfig;
    }

    public Set<Animal> getAnimalConfig(){
        return animalConfig;
    }

    public Set<Plants> getPlantConfig(){
        return plantConfig;
    }

    public Set<Item> getItemConfig() {
        return itemConfig ;
    }
}