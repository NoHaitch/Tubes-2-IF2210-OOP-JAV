package plugin;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;

import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import javva.tubes2.Player;
import javva.tubes2.dataLoader.DataLoader;

/**
 * Plugin to save and load data to YAML file
 */
public class YAMLDataLoader implements DataLoader {
    /**
     * Save player data to YAML file
     *
     * @param player   Player data
     * @param filePath relative path to result folder
     * @throws Exception file not found, failed to save
     */
    public void savePlayer(Player player, String filePath) throws Exception {

    }

    /**
     * Save game data to YAML file
     *
     * @param filePath relative path to result folder
     * @throws Exception file not found, failed to save
     */
    public void saveGameState(String filePath) throws Exception {

    }

    /**
     * Load player data from YAML file
     *
     * @param filePath relative path to result folder
     * @return Player object
     * @throws Exception file not found, failed to load
     */
    public Player loadPlayer(String filePath) throws Exception {
        return null;
    }

    /**
     * Load game state from YAML file
     *
     * @param filePath relative path to result folder
     * @return Game state object
     * @throws Exception file not found, failed to load
     */
    public Object loadGameState(String filePath) throws Exception {
        return null;
    }
}
