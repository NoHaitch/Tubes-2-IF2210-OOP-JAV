package javva.tubes2.dataLoader;

import java.util.*;
import java.io.File;

import javva.tubes2.Player.Player;

/**
 * Managed game save and load <br>
 * Singleton pattern
 */
public class SaveManager {

    /**
     * format_map contains all save format available as key and value is a class that implements DataLoader<br>
     * key = format <br>
     * value = class <br>
     */
    private Map<String, Class<?>> format_map;

    /**
     * Private Constructor
     */
    private SaveManager() {
        // initialize data
        format_map = new HashMap<>();

        // add default save format
        format_map.put("txt", TXTDataLoader.class);
    }

    /**
     * Singleton pattern
     */
    private static SaveManager instance = null;

    public static SaveManager getInstance() {
        if (instance == null) {
            instance = new SaveManager();
        }
        return instance;
    }

    /**
     * Get all save formats available
     */
    public List<String> getSaveFormats() {
        return new ArrayList<>(format_map.keySet());
    }

    /**
     * Get the DataLoader that implements the save format
     *
     * @param format file format
     * @return Dataloader class for format
     */
    public Class<?> getDataLoaderClass(String format) {
        return format_map.get(format);
    }

    /**
     * Add new save format
     *
     * @param format          file format
     * @param dataLoaderClass class that implements DataLoader
     */
    public void addSaveFormat(String format, Class<?> dataLoaderClass) {
        format_map.put(format, dataLoaderClass);
    }

    /**
     * Load dataloader plugins from jar file
     *
     * @param jarPath relative path to jar file
     */
    public void loadPlugins(String jarPath) throws Exception {
        try {
            // load plugins
            List<?> classesLoaded = PluginLoader.loadClassesFromJar(jarPath);

            for(Object obj : classesLoaded) {
                Class<?> dataLoaderClass = (Class<?>) obj;
                DataLoader dataLoader = (DataLoader) dataLoaderClass.getDeclaredConstructor().newInstance();
                addSaveFormat(dataLoader.getFileFormat(), dataLoaderClass);
            }


        } catch (Exception e) {
            throw new Exception("[SaveManager] Failed to load plugins");
        }
    }

    /**
     * Save the Game
     *
     * @param player1    player 1 object
     * @param player2    player 2 object
     *                                     TODO: pass Game object
     * @param folderPath relative path to save folder
     * @param format     file format
     */
    public void saveGame(Player player1, Player player2, String folderPath, String format) throws Exception {
        // check format
        Class<?> dataLoaderClass = getDataLoaderClass(format);
        if (dataLoaderClass == null) {
            throw new Exception("Format not found");
        }

        // save game
        try {
            // get data loader class
            DataLoader dataLoader = (DataLoader) dataLoaderClass.getDeclaredConstructor().newInstance();

            // create folder if folder not exist
            File folder = new File(folderPath);
            if (!folder.exists()) {
                System.out.println("[SaveManager] Folder not found: " + folderPath);
                if (folder.mkdirs()) {
                    System.out.println("[SaveManager] Folder created at: " + folderPath);
                } else {
                    System.out.println("[SaveManager] Failed to create folder at: " + folderPath);
                }
            }

            // save game state
            dataLoader.saveGameState(folderPath + "/gamestate." + format);

            // save player1
            dataLoader.savePlayer(player1, folderPath + "/player1." + format);

            // save player 2
            dataLoader.savePlayer(player2, folderPath + "/player2." + format);

        } catch (Exception e) {
            throw new Exception("Failed to save game");
        }
    }

    /**
     * Load player object from file
     *
     * @param folderPath relative path to save folder
     * @param filename file name without extension
     * @param format file format
     * @return player object
     * @throws Exception file not found, corrupted save
     */
    public Player loadPlayer(String folderPath, String filename, String format) throws Exception {
        Class<?> dataLoaderClass = getDataLoaderClass(format);
        if (dataLoaderClass == null) {
            throw new Exception("Format not found");
        }

        try {
            DataLoader dataLoader = (DataLoader) dataLoaderClass.getDeclaredConstructor().newInstance();

            return dataLoader.loadPlayer(folderPath + "/" + filename + "." + format);

        } catch (Exception e) {
            throw new Exception("Failed to load player object");
        }
    }

    /**
     * Load game state from file
     *
     * @param folderPath relative path to save folder
     * @param format     file format
     * @return Game object
     * @throws Exception file not found, corrupted save
     */
    public Object loadGameState(String folderPath, String format) throws Exception {
        Class<?> dataLoaderClass = getDataLoaderClass(format);
        if (dataLoaderClass == null) {
            throw new Exception("Format not found");
        }

        try {
            DataLoader dataLoader = (DataLoader) dataLoaderClass.getDeclaredConstructor().newInstance();

            return dataLoader.loadGameState(folderPath + "/gamestate." + format);

        } catch (Exception e) {
            throw new Exception("Failed to load game state");
        }
    }
}
