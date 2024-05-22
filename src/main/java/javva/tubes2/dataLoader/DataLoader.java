package javva.tubes2.dataLoader;

import javva.tubes2.Player;

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
     * @param filePath relative path to result folder
     * @throws Exception file not found, failed to save
     */
    void savePlayer(Player player, String filePath) throws Exception;

    /**
     * Save game data to file
     *
     * @param filePath relative path to result folder
     * @throws Exception file not found, failed to save
     */
    void saveGameState(String filePath) throws Exception;

    /**
     * Load player data from file
     *
     * @param filePath relative path to result folder
     * @return Player object
     * @throws Exception file not found, failed to load
     */
    Player loadPlayer(String filePath) throws Exception;

    /**
     * Load game state from file
     *
     * @param filePath relative path to result folder
     * @return Game state object
     * @throws Exception file not found, failed to load
     */
    Object loadGameState(String filePath) throws Exception;
}
