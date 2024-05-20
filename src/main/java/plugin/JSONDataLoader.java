package plugin;

import java.io.File;
import java.lang.reflect.Field;

import com.fasterxml.jackson.databind.ObjectMapper;
import javva.tubes2.dataLoader.DataLoader;

/**
 * Plugin to save and load data to JSON file
 */
public class JSONDataLoader implements DataLoader {

    /**
     * Save data to JSON file
     * @param data object that will be saved to json save file
     * @param filePath relative path to new json save file
     * @throws Exception data not correct, failed to save
     */
    @Override
    public void saveData(Object data, String filePath) throws Exception {
        // get all attributes and their value
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            // check if variable is initialized or exist
            if (field.get(data) == null) {
                throw new Exception("Save error, " + field.getName() + " is null");
            }
        }

        // save file to json
        ObjectMapper mapper = new ObjectMapper();

        // Write object to JSON file
        mapper.writeValue(new File(filePath), data);
    }

    /**
     * Load data from JSON file
     * @param filePath relative path to json save file
     * @param className fully qualified name of the class
     * @return data object
     * @throws Exception data not correct, corrupted save
     */
    @Override
    public Object loadData(String filePath, String className) throws Exception {
        // load file from json
        ObjectMapper mapper = new ObjectMapper();

        // create class object from class name
        Class<?> objclass = Class.forName(className);

        // Read object from JSON file
        return mapper.readValue(new File(filePath), objclass);
    }
}