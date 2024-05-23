package plugin;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.ObjectMapper;
import javva.tubes2.Player.Player;
import javva.tubes2.dataLoader.DataLoader;

/**
 * Plugin to save and load data to JSON file
 */
public class JSONDataLoader implements DataLoader {
    /**
     * @return file format
     */
    @Override
    public String getFileFormat() {
        return "json";
    }

    /**
     * Save player data to JSON file
     *
     * @param player   Player data
     * @param filePath relative path to result folder
     * @throws Exception file not found, failed to save
     */
    @Override
    public void savePlayer(Player player, String filePath) throws Exception {
        // Create ObjectMapper instances
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert Player object to JSON string
        String jsonString = objectMapper.writeValueAsString(player);

        // Write JSON string to file
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(jsonString);
        fileWriter.flush();
        fileWriter.close();
    }

    /**
     * Save game data to JSON file
     *
     * @param filePath relative path to result folder
     * @throws Exception file not found, failed to save
     */
    @Override
    public void saveGameState(String filePath) throws Exception {

    }

    /**
     * Load player data from JSON file
     *
     * @param filePath relative path to result folder
     * @return Player object, null if failed to load
     * @throws Exception file not found, failed to load
     */
    @Override
    public Player loadPlayer(String filePath) throws Exception {

        return null;
    }

    /**
     * Load game state from JSON file
     *
     * @param filePath relative path to result folder
     * @return Game state object, null if failed to load
     * @throws Exception file not found, failed to load
     */
    @Override
    public Object loadGameState(String filePath) throws Exception {


        return null;
    }
}