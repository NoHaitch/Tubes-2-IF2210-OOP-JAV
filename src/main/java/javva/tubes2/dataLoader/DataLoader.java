package javva.tubes2.dataLoader;

import javafx.util.Pair;
import javva.tubes2.Player.Player;
import javva.tubes2.Shop;


/**
 * Data Loader Interface  <br>
 * Save and Load data from file <br>
 * Used to create plugins for saving and loading data
 */
public interface DataLoader {
    /**
     * Save player data to file
     *
     * @param player   Player data
     * @param file_path relative path to result folder
     * @throws Exception file not found, failed to save
     */
    void savePlayer(Player player, String file_path) throws Exception;

    /**
     * Save game data to file
     *
     * @param file_path relative path to result folder
     * @param shop Shop object
     * @param current_turn current game turn
     * @throws Exception file not found, failed to save
     */
    void saveGameState(String file_path, Shop shop, Integer current_turn) throws Exception;

    /**
     * Load player data from file
     *
     * @param file_path relative path to result folder
     * @return Player object
     * @throws Exception file not found, failed to load
     */
    Player loadPlayer(String file_path) throws Throwable;

    /**
     * Load game state from file
     *
     * @param file_path relative path to result folder
     * @return Pair, first is Shop, second is turn
     * @throws Exception file not found, failed to load
     */
    Pair<Shop, Integer> loadGameState(String file_path) throws Exception;

    /**
     * @return file format
     */
    String getFileFormat();
}
