package javva.tubes2.dataLoader;

import javva.tubes2.Player.Player;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * Plugin to save and load data to Text file
 */
public class TXTDataLoader implements DataLoader {
    /**
     * File format for this plugin
     */
    private final String format = "txt";

    /**
     * @return file format
     */
    @Override
    public String getFileFormat() {
        return format;
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

        // save active card amount
        writer.println(3);

        // save active cards
        Map<String, String> tempActive = new HashMap<>();
        tempActive.put("A01", "BERUANG");
        tempActive.put("B01", "BERUANG");
        tempActive.put("C01", "BERUANG");

        for (String key : tempActive.keySet()) {
            writer.print(key + " ");
            writer.println(tempActive.get(key));
        }

        // save field card amount
        writer.println(1);

        // save field cards location
        String location = "A01";
        String card = "DOMBA";
        int weight = 10;
        int amountOfItems = 3;
        List<String> items = new ArrayList<>();
        items.add("ACCELERATE");
        items.add("DELAY");
        items.add("PROTECT");
        writer.print(location + " " + card + " " + weight + " " + amountOfItems + " ");
        for (String item : items) {
            writer.print(item + " ");
        }
        writer.println();

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
        // TODO: USE PLAYER ATTRIBUTE
        // load file
        Scanner scanner = new Scanner(new File(filePath));

        // get gulden
        int gulden = scanner.nextInt();
        System.out.println("[LoadPlayer] Gulden: " + gulden);

        // get deck amoung
        int deck_amount = scanner.nextInt();
        System.out.println("[LoadPlayer] Deck Amount: " + deck_amount);

        /// get active card amount
        int active_card_amount = scanner.nextInt();
        System.out.println("[LoadPlayer] Active Card Amount: " + active_card_amount);

        // get active cards
        for (int i = 0; i < active_card_amount; i++) {
            String location = scanner.next();
            String card = scanner.next();
            System.out.println("[LoadPlayer] Active Card: " + location + " " + card);
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