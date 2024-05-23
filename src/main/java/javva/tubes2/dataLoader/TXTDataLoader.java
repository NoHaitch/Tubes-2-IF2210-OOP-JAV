package javva.tubes2.dataLoader;

import javva.tubes2.Player.Field;
import javva.tubes2.Player.Player;
import javva.tubes2.Card.Card;
import javva.tubes2.Card.Plants;
import javva.tubes2.Card.Product;
import javva.tubes2.Card.Animal;
import javva.tubes2.CardConfig;

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
     * @param filePath relative path to result folder
     * @throws Exception data not correct, failed to save
     */
    public void savePlayer(Player player, String filePath) throws Exception {
        // initialize file
        PrintWriter writer = new PrintWriter(filePath);

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
        for(int i = 0; i < 20; i++){
            try{
                Card card = field.getElement(i);
                if(!card.getName().equals("null")){
                    count++;
                }

            } catch (Throwable e){
                throw new Exception(e.getMessage());
            }
        }
        writer.println(count);

        // save field cards
        for(int i = 0; i < 20; i++){
            try{
                Card card = field.getElement(i);
                if(!card.getName().equals("null")){
                    count++;
                }

                // save item location and name
                writer.print((i + 1) + " ");
                writer.print(card.getName() + " ");

                // save item progress and items
                if (card instanceof Plants plant) {
                    writer.print(plant.getProgress() + " ");
                    // TODO: add item amount
                    // TODO: add items
                }
            } catch (Throwable e){
                throw new Exception(e.getMessage());
            }
        }

        writer.close();
    }

    /**
     * save game data to text file
     *
     * @param filePath relative path to result folder
     * @throws Exception data not correct, failed to save
     */
    public void saveGameState(String filePath) throws Exception {
        // TODO: USE GAME ATTRIBUTE
        // initialize file
        PrintWriter writer = new PrintWriter(filePath);

        // save current turn
        writer.println(1);

        // save amount of shop items
        writer.println(5);

        // Save shop items
        Map<String, Integer> tempShop = new HashMap<>();
        tempShop.put("SIRIP_HIU", 5);
        tempShop.put("SUSU", 2);
        tempShop.put("DAGING_DOMBA", 3);
        tempShop.put("DAGING_KUDA", 10);
        tempShop.put("DAGING_BERUANG", 1);

        for (String key : tempShop.keySet()) {
            writer.print(key + " ");
            writer.println(tempShop.get(key));
        }

        writer.close();
    }

    /**
     * Load player data from text file
     *
     * @param filePath relative path to result folder
     * @return Player object, null if failed to load
     * @throws Exception data not correct, corrupted save
     */
    public Player loadPlayer(String filePath) throws Exception {
        // load file
        Scanner scanner = new Scanner(new File(filePath));

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
        CardConfig card_config = new CardConfig();
        List<Animal> animal_config = card_config.getAnimalConfig();
        List<Plants> plant_config = card_config.getPlantConfig();
        List<Product> product_config = card_config.getProductConfig();

        List<Card> active_cards = new ArrayList<>(6);
        Map<Integer, String> temp_active_name = new HashMap<>();

        // get active card data
        for(int i = 0; i < active_card_amount; i++){
            int location = scanner.nextInt();
            String card_name = scanner.next();
            temp_active_name.put(location, card_name);
        }

        // place on active deck
        for(int i = 0; i < 6; i++){
            if(temp_active_name.containsKey(i)) {
                boolean found = false;
                for (Animal animal : animal_config) {
                    if (animal.getName().equalsIgnoreCase(temp_active_name.get(i))) {
                        Animal new_animal = new Animal(animal);
                        player.addToActiveDeck(new_animal);
                        found = true;
                        break;
                    }
                }

                for (Plants plant : plant_config){
                    if(found){
                        break;
                    }

                    if (plant.getName().equalsIgnoreCase(temp_active_name.get(i))) {
                        Plants new_plant = new Plants(plant);
                        player.addToActiveDeck(new_plant);
                        found = true;
                        break;
                    }
                }

                for (Product product : product_config){
                    if(found){
                        break;
                    }

                    if (product.getName().equalsIgnoreCase(temp_active_name.get(i))) {
                        Product new_product = new Product(product);
                        player.addToActiveDeck(new_product);
                        found = true;
                        break;
                    }
                }

                // TODO: Check items

                throw new Exception("Card not found");
            }
        }

        // get field cards
        Map<Integer, String> temp_name = new HashMap<>();
        Map<Integer, Integer> temp_progres = new HashMap<>();
        Map<Integer, List<String>> temp_item = new HashMap<>();

        // get field cards data
        for (int i = 0; i < active_card_amount; i++) {
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
        for(int i = 1; i < active_card_amount+1; i++){
            boolean found = false;
            for(Animal animal : animal_config){
                if(found){
                    break;
                }
                if(animal.getName().equalsIgnoreCase(temp_name.get(i))){
                    Animal new_animal = new Animal(animal);
                    new_animal.setWeight(temp_progres.get(i));
                    // TODO: add item
                    player.addToActiveDeck(new_animal);
                    found = true;
                }
            }

            for(Plants plant : plant_config){
                if(found){
                    break;
                }

                if(plant.getName().equalsIgnoreCase(temp_name.get(i))){
                    Plants new_plant = new Plants(plant);
                    new_plant.setProgress(temp_progres.get(i));
                    // TODO: add item
                    player.addToActiveDeck(new_plant);
                    found = true;
                }
            }
        }

        scanner.close();
        return player;
    }

    /**
     * Load game data from text file
     *
     * @param filePath relative path to result folder
     * @return Game data, null if failed to load
     * @throws Exception data not correct, corrupted save
     */
    public Object loadGameState(String filePath) throws Exception {
        // TODO: USE GAME ATTRIBUTE
        // load file
        Scanner scanner = new Scanner(new File(filePath));

        // load current turn
        int turn = scanner.nextInt();
        System.out.println("[LoadGameState] Turn: " + turn);

        // save amount of shop items
        int shop_item_amount = scanner.nextInt();
        System.out.println("[LoadGameState] Shop Item Amount: " + shop_item_amount);

        // Save shop items
        Map<String, Integer> tempShop = new HashMap<>();
        for (int i = 0; i < shop_item_amount; i++) {
            String item_name = scanner.next();
            int item_amount = scanner.nextInt();
            tempShop.put(item_name, item_amount);
            System.out.println("[LoadGameState] Shop Item: " + item_name + " " + item_amount);
        }

        scanner.close();
        return null;
    }
}