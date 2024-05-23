package javva.tubes2.dataLoader;

import javva.tubes2.Player.Field;
import javva.tubes2.Player.Player;
import javva.tubes2.Card.Card;
import javva.tubes2.Card.Plants;
import javva.tubes2.Card.Animal;

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
//        Field field = player.getField();
        int count = 0;
//        for(int i = 0; i < 20; i++){
//            Card card = field.getElement(i);
//            if(!card.getName().equals("null")){
//                count++;
//            }
//        }
        writer.println(count);

//        for(int i = 0; i < 20; i++){
//            Card card = field.getElement(i);
//            if(card.getName().equals("null")){
//                continue;
//            }
//
//            // save item location and name
//            writer.print((i + 1) + " ");
//            writer.print(card.getName() + " ");

//            // save item progress and items
//            if (card instanceof Plants) {
//                Plants plant = (Plants) card;
//                writer.print(plant.getProgress() + " ");
//                // TODO: add item amount
//            }
//        }

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
        List<Card> activeCards = new ArrayList<>(active_card_amount);
        for (int i = 0; i < active_card_amount; i++) {
            String location = scanner.next();
            String card_name = scanner.next();
            Card card = new Card(card_name, "ACTIVE");
            player.addToActiveDeck(card);
        }

        // get field card amount
        int field_card_amount = scanner.nextInt();
        System.out.println("[LoadPlayer] Field Card Amount: " + field_card_amount);

        // get field cards
        for (int i = 0; i < field_card_amount; i++) {
            String location = scanner.next();
            String card = scanner.next();
            int weight = scanner.nextInt();
            int amountOfItems = scanner.nextInt();
            System.out.println("[LoadPlayer] Field Card: " + location + " " + card + " " + weight + " " + amountOfItems + " ");
            List<String> items = new ArrayList<>();
            for (int j = 0; j < amountOfItems; j++) {
                items.add(scanner.next());
                System.out.println("[LoadPlayer] " + (j + 1) + " Item: " + items.get(j));
            }

        }

        scanner.close();
        return null;
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