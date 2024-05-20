package plugin;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;

import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import javva.tubes2.dataLoader.DataLoader;

/**
 * Plugin to save and load data to YAML file
 */
public class YAMLDataLoader implements DataLoader {

    /**
     * Save data to YAML file
     * @param data object that will be saved to YAML save file
     * @param filePath relative path to new YAML save file
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

        // save file to YAML
        YAMLMapper mapper = new YAMLMapper();
        mapper.configure(YAMLGenerator.Feature.MINIMIZE_QUOTES, true);
        FileWriter writer = new FileWriter(filePath);
        mapper.writeValue(writer, data);
    }

    /**
     * Load data from YAML file
     * @param filePath relative path to YAML save file
     * @param className fully qualified name of the class
     * @return data object
     * @throws Exception data not correct, corrupted save
     */
    @Override
    public Object loadData(String filePath, String className) throws Exception {
        // load file from YAML
        YAMLMapper mapper = new YAMLMapper();

        // create class object from class name
        Class<?> objclass = Class.forName(className);

        // Read object from YAML file
        return mapper.readValue(new File(filePath), objclass);
    }
}
