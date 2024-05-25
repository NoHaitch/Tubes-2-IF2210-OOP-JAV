package plugin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javafx.util.Pair;
import javva.tubes2.Card.*;
import javva.tubes2.Player.Field;
import javva.tubes2.Player.Player;
import javva.tubes2.Shop;
import javva.tubes2.CardConfig;
import javva.tubes2.dataLoader.DataLoader;

import java.io.File;
import java.util.*;

/**
 * Plugin to save and load data to JSON file
 */
public class JSONDataLoader implements DataLoader {
    // object mapper
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);


    /**
     * Save player data to json file
     *
     * @param player   Player data
     * @param file_path relative path to result folder
     * @throws Exception file not found, failed to save
     */
    @Override
    public void savePlayer(Player player, String file_path) throws Exception {
        Map<String, Object> playerData = new HashMap<>();

        // save gulden
        playerData.put("gulden", player.getGulden());

        // save deck amount
        playerData.put("deckAmount", player.getCapacity());

        // save active deck
        List<String> activeCardNames = new ArrayList<>();
        for (Card card : player.getActiveDeck()) {
            activeCardNames.add(card.getName());
        }
        playerData.put("activeCards", activeCardNames);

        Field field = player.getField();
        List<Map<String, Object>> fieldCards = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            try {
                Card card = field.getElement(i);
                if (!card.getName().equals("null")) {
                    Map<String, Object> cardData = new HashMap<>();
                    cardData.put("location", i + 1);
                    cardData.put("name", card.getName());
                    if (card instanceof Plants plant) {
                        cardData.put("progress", plant.getProgress());
                        cardData.put("protect", plant.getProtect());
                        cardData.put("trap", plant.getTrap());
                    } else {
                        Animal animal = (Animal) card;
                        cardData.put("weight", animal.getWeight());
                        cardData.put("protect", animal.getProtect());
                        cardData.put("trap", animal.getTrap());
                    }
                    fieldCards.add(cardData);
                }
            } catch (Throwable e) {
                throw new Exception(e.getMessage());
            }
        }
        playerData.put("fieldCards", fieldCards);

        objectMapper.writeValue(new File(file_path), playerData);
    }

    /**
     * Save game data to json file
     *
     * @param file_path relative path to result folder
     * @param shop Shop object
     * @param current_turn current game turn
     * @throws Exception file not found, failed to save
     */
    @Override
    public void saveGameState(String file_path, Integer current_turn) throws Exception {
        Shop shop = Shop.getInstance();
        Map<String, Object> gameState = new HashMap<>();
        gameState.put("currentTurn", current_turn);

        List<Map<String, Object>> shopItems = new ArrayList<>();
        List<Map.Entry<String, Integer>> shop_item_list = shop.getShopItemsList();

        for (Map.Entry<String, Integer> entry : shop_item_list) {
            Map<String, Object> itemData = new HashMap<>();
            itemData.put("name", entry.getKey());
            itemData.put("amount", entry.getValue());
            shopItems.add(itemData);
        }
        gameState.put("shopItems", shopItems);

        objectMapper.writeValue(new File(file_path), gameState);
    }

    /**
     * Load player data from file
     *
     * @param file_path relative path to result folder
     * @return Player object
     * @throws Exception file not found, failed to load
     */
    public Player loadPlayer(String file_path) throws Throwable {
        Map<String, Object> playerData = objectMapper.readValue(new File(file_path), new TypeReference<>() {
        });
        CardConfig card_config = CardConfig.getInstance();
        List<Animal> animal_config = card_config.getAnimalConfig();
        List<Plants> plant_config = card_config.getPlantConfig();
        List<Product> product_config = card_config.getProductConfig();
        List<Item> item_config = card_config.getItemConfig();


        int gulden = (int) playerData.get("gulden");
        int deckAmount = (int) playerData.get("deckAmount");
        Player player = new Player(gulden, deckAmount);

        List<String> activeCardNames = (List<String>) playerData.get("activeCards");
        for (int i = 0; i < 6; i++) {
            boolean found = false;
            for (Animal animal : animal_config) {
                if (animal.getName().equalsIgnoreCase(activeCardNames.get(i))) {
                    Animal new_animal = new Animal(animal);
                    player.addToActiveDeck(new_animal, i);
                    found = true;
                    break;
                }
            }

            for (Plants plant : plant_config) {
                if (found) {
                    break;
                }

                if (plant.getName().equalsIgnoreCase(activeCardNames.get(i))) {
                    Plants new_plant = new Plants(plant);
                    player.addToActiveDeck(new_plant, i);
                    found = true;
                    break;
                }
            }

            for (Product product : product_config) {
                if (found) {
                    break;
                }

                if (product.getName().equalsIgnoreCase(activeCardNames.get(i))) {
                    Product new_product = new Product(product);
                    player.addToActiveDeck(new_product, i);
                    found = true;
                    break;
                }
            }

            for (Item item : item_config) {
                if (found) {
                    break;
                }

                if (item.getName().equalsIgnoreCase(activeCardNames.get(i))) {
                    Item new_item = new Item(item);
                    player.addToActiveDeck(new_item, i);
                    break;
                }
            }

            if (!found) {
                throw new Exception("Card not found");
            }
        }

        List<Map<String, Object>> fieldCards = (List<Map<String, Object>>) playerData.get("fieldCards");
        for (Map<String, Object> cardData : fieldCards) {
            int location = (int) cardData.get("location") - 1;
            String name = (String) cardData.get("name");
            if (name != null) {
                boolean protect = (boolean) cardData.getOrDefault("protect", false);
                boolean trap = (boolean) cardData.getOrDefault("trap", false);

                boolean found = false;
                for (Animal animal : animal_config) {
                    if (animal.getName().equalsIgnoreCase(name)) {
                        Animal new_animal = new Animal(animal);
                        new_animal.setProtect(protect);
                        new_animal.setTrap(trap);
                        int weight = (int) cardData.get("weight");
                        new_animal.setWeight(weight);
                        player.addField(new_animal, location);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    for (Plants plant : plant_config) {
                        if (plant.getName().equalsIgnoreCase(name)) {
                            Plants new_plant = new Plants(plant);
                            new_plant.setProtect(protect);
                            new_plant.setTrap(trap);
                            int progress = (int) cardData.get("progress");
                            new_plant.setProgress(progress);
                            player.addField(new_plant, location);
                            break;
                        }
                    }
                }
            }

        }

        return player;
    }


    /**
     * Load game state from file
     *
     * @param file_path relative path to result folder
     * @return Pair, first is Shop, second is turn
     * @throws Exception file not found, failed to load
     */
    @Override
    public Integer loadGameState(String file_path) throws Exception {
        Map<String, Object> gameState = objectMapper.readValue(new File(file_path), new TypeReference<>() {
        });
        Shop shop = Shop.getInstance();

        int currentTurn = (int) gameState.get("currentTurn");

        List<Map<String, Object>> shopItems = (List<Map<String, Object>>) gameState.get("shopItems");
        for (Map<String, Object> itemData : shopItems) {
            String name = (String) itemData.get("name");
            int amount = (int) itemData.get("amount");
            for (int i = 0; i < amount; i++) shop.addProduct(name);
        }

        return currentTurn;
    }

    /**
     * @return file format
     */
    @Override
    public String getFileFormat() {
        return "json";
    }
}
