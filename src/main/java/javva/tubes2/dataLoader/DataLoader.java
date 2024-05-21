package javva.tubes2.dataLoader;

/**
 *  Data Loader Interface  <br>
 *  Save and Load data from file <br>
 *  Used to create plugins for saving and loading data
 */
public interface DataLoader {
    /**
     * Save data to filepath
     *
     * @param data object that will be saved
     * @param filePath relative path to result file
     * @throws Exception data not correct, etc
     */
    void saveData(Object data, String filePath) throws Exception;

    /**
     * Load data from filepath
     *
     * @param filePath relative path to result file
     * @param className fully qualified name of the class
     * @return data object
     * @throws Exception data not correct, corrupted save
     */
    Object loadData(String filePath, String className) throws Exception;
}