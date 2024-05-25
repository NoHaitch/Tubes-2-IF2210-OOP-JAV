package javva.tubes2.dataLoader;

import javafx.util.Pair;
import javva.tubes2.Card.*;
import javva.tubes2.Player.Field;
import javva.tubes2.Player.Player;
import javva.tubes2.CardConfig;
import javva.tubes2.Shop;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Plugin to save and load data to Text file
 */
public class TXTDataLoader implements DataLoader {

    /**
     * @return file format
     */
    @Override
    public String getFileFormat() {
        return "txt";
    }

    /**
     * Save player data to text file
     *
     * @param player   Player data
     * @param file_path relative path to result folder
     * @throws Exception data not correct, failed to save
     */
    public void savePlayer(Player player, String file_path) throws Exception {
        // initialize file
        PrintWriter writer = new PrintWriter(file_path);

        // save gulden
        writer.println(player.getGulden());

        // save deck amount
        writer.println(player.getCapacity());

        // get active deck
        List<Card> activeCards = player.getActiveDeck();
        int activeCardSize = activeCards.size();

        // save active card amount
        writer.println(activeCardSize);

        // save active cards
        for (int i = 0; i < activeCardSize; i++) {
            writer.print((i + 1) + " ");
            writer.println(activeCards.get(i).getName());
        }

        // save field card amount
        Field field = player.getField();
        int count = 0;
        for (int i = 0; i < 20; i++) {
            try {
                Card card = field.getElement(i);
                if (!card.getName().equals("null")) {
                    count++;
                }

            } catch (Throwable e) {
                throw new Exception(e.getMessage());
            }
        }
        writer.println(count);

        // save field cards
        for (int i = 0; i < 20; i++) {
            try {
                Card card = field.getElement(i);
                if (!card.getName().equals("null")) {
                    count++;
                }

                // save item location and name
                writer.print((i + 1) + " ");
                writer.print(card.getName() + " ");

                // save item progress and items
                if (card instanceof Plants plant) {
                    writer.print(plant.getProgress() + " ");

                    int itemAmount = 0;
                    if (plant.getProtect()) itemAmount++;
                    if (plant.getTrap()) itemAmount++;

                    writer.println(itemAmount);
                    if (plant.getProtect()) writer.println("PROTECT ");
                    if (plant.getTrap()) writer.println("TRAP ");
                } else {
                    Animal animal = (Animal) card;
                    writer.print(animal.getWeight() + " ");

                    int itemAmount = 0;
                    if (animal.getProtect()) itemAmount++;
                    if (animal.getTrap()) itemAmount++;

                    writer.println(itemAmount);
                    if (animal.getProtect()) writer.println("PROTECT ");
                    if (animal.getTrap()) writer.println("TRAP ");
                }
            } catch (Throwable e) {
                throw new Exception(e.getMessage());
            }
        }

        writer.close();
    }

    /**
     * Save game data to file
     *
     * @param file_path relative path to result folder
     * @param shop Shop object
     * @param current_turn current game turn
     * @throws Exception file not found, failed to save
     */
    @Override
    public void saveGameState(String file_path, Shop shop, Integer current_turn) throws Exception {
        // initialize file
        PrintWriter writer = new PrintWriter(file_path);

        // save current turn
        writer.println(current_turn);

        // shop
        List<Map.Entry<String, Integer>> shop_item_list = shop.getShopItemsList();

        // save amount of shop items
        writer.println(shop_item_list.size());

        for (Map.Entry<String, Integer> item : shop_item_list) {
            writer.println(item.getKey() + " " + item.getValue());
        }

        writer.close();
    }

    /**
     * Load player data from text file
     *
     * @param file_path relative path to result folder
     * @return Player object, null if failed to load
     * @throws Exception data not correct, corrupted save
     */
    public Player loadPlayer(String file_path) throws Exception {
        // load file
        Scanner scanner = new Scanner(new File(file_path));

        // get gulden
        int gulden = scanner.nextInt();
        System.out.println("[LoadPlayer] Gulden: " + gulden);

        // get deck amount
        int deck_amount = scanner.nextInt();
        System.out.println("[LoadPlayer] Deck Amount: " + deck_amount);

        // initialize player
        Player player = new Player(gulden, deck_amount);

        // get active card amount
        int active_card_amount = scanner.nextInt();
        System.out.println("[LoadPlayer] Active Card Amount: " + active_card_amount);

        // get active cards
        CardConfig card_config = CardConfig.getInstance();
        List<Animal> animal_config = card_config.getAnimalConfig().stream().toList();
        List<Plants> plant_config = card_config.getPlantConfig().stream().toList();
        List<Product> product_config = card_config.getProductConfig().stream().toList();
        List<Item> item_config = card_config.getItemConfig().stream().toList();

        List<Card> active_cards = new ArrayList<>(6);
        Map<Integer, String> temp_active_name = new HashMap<>();

        // get active card data
        for (int i = 0; i < active_card_amount; i++) {
            int location = scanner.nextInt();
            String card_name = scanner.next();
            temp_active_name.put(location, card_name);
        }

        // place on active deck
        for (int i = 0; i < 6; i++) {
            if (temp_active_name.containsKey(i)) {
                boolean found = false;
                for (Animal animal : animal_config) {
                    if (animal.getName().equalsIgnoreCase(temp_active_name.get(i))) {
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

                    if (plant.getName().equalsIgnoreCase(temp_active_name.get(i))) {
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

                    if (product.getName().equalsIgnoreCase(temp_active_name.get(i))) {
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

                    if (item.getName().equalsIgnoreCase(temp_active_name.get(i))) {
                        Item new_item = new Item(item);
                        player.addToActiveDeck(new_item, i);
                        break;
                    }
                }

                if (!found) {
                    throw new Exception("Card not found");
                }
            }
        }

        // get field card amount
        int field_card_amount = scanner.nextInt();

        // get field cards
        Map<Integer, String> temp_name = new HashMap<>();
        Map<Integer, Integer> temp_progres = new HashMap<>();
        Map<Integer, List<String>> temp_item = new HashMap<>();

        // get field cards data
        for (int i = 0; i < field_card_amount; i++) {
            Integer location = scanner.nextInt();
            String card_name = scanner.next();
            int progress = scanner.nextInt();
            int item_amount = scanner.nextInt();

            temp_name.put(location, card_name);
            temp_progres.put(location, progress);
            List<String> items = new ArrayList<>();
            for (int j = 0; j < item_amount; j++) {
                items.add(scanner.next());
            }
            temp_item.put(location, items);
        }

        // add all cards sorted using location
        for (int i = 1; i < field_card_amount + 1; i++) {
            boolean found = false;
            for (Animal animal : animal_config) {
                if (found) {
                    break;
                }
                if (animal.getName().equalsIgnoreCase(temp_name.get(i))) {
                    Animal new_animal = new Animal(animal);
                    new_animal.setWeight(temp_progres.get(i));

                    for (String elmt : temp_item.get(i)) {
                        if (elmt.equalsIgnoreCase("PROTECT")) {
                            new_animal.setProtect(true);
                        } else if (elmt.equalsIgnoreCase("TRAP")) {
                            new_animal.setTrap(true);
                        }
                    }

                    player.addField(new_animal, i - 1);
                    found = true;
                }
            }

            for (Plants plant : plant_config) {
                if (found) {
                    break;
                }

                if (plant.getName().equalsIgnoreCase(temp_name.get(i))) {
                    Plants new_plant = new Plants(plant);
                    new_plant.setProgress(temp_progres.get(i));

                    for (String elmt : temp_item.get(i)) {
                        if (elmt.equalsIgnoreCase("PROTECT")) {
                            new_plant.setProtect(true);
                        } else if (elmt.equalsIgnoreCase("TRAP")) {
                            new_plant.setTrap(true);
                        }
                    }

                    player.addField(new_plant, i - 1);
                    found = true;
                }
            }
        }

        scanner.close();
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
    public Pair<Shop, Integer> loadGameState(String file_path) throws Exception {
        // load file
        Scanner scanner = new Scanner(new File(file_path));

        // load current turn
        int turn = scanner.nextInt();
        System.out.println("[LoadGameState] Turn: " + turn);

        Shop shop = Shop.getInstance();

        // save amount of shop items
        int shop_item_amount = scanner.nextInt();
        System.out.println("[LoadGameState] Shop Item Amount: " + shop_item_amount);

        // Save shop items
        for (int i = 0; i < shop_item_amount; i++) {
            String item_name = scanner.next();
            int item_amount = scanner.nextInt();
            for (int j = 0; j < item_amount; j++) shop.addProduct(item_name);
            System.out.println("[LoadGameState] Shop Item: " + item_name + " " + item_amount);
        }

        scanner.close();
        return new Pair<>(shop, turn);
    }
}