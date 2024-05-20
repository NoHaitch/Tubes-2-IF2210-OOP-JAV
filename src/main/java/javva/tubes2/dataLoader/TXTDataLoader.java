package javva.tubes2.dataLoader;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Plugin to save and load data to Text file
 */
public class TXTDataLoader implements DataLoader {

    /**
     * Save data to Text file
     *
     * @param data     object that will be saved to text save file
     * @param filePath relative path to new text save file
     * @throws Exception data not correct, failed to save
     */
    @Override
    public void saveData(Object data, String filePath) throws Exception {
        // get all attributes and their value
        Field[] fields = data.getClass().getDeclaredFields();

        // use printwriter to write to file
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (Field field : fields) {
                // get private field
                field.setAccessible(true);

                // check if variable is initialized or exist
                Object value = field.get(data);
                if (value == null) {
                    throw new Exception("Save error, " + field.getName() + " is null");
                }

                // write to file
                if (value instanceof List || value instanceof Map || value.getClass().isArray()) {
                    writer.println(value.toString());
                } else {
                    writer.println(value);
                }
            }
        }
    }

    /**
     * Load data from Text file
     *
     * @param filePath  relative path to text save file
     * @param className fully qualified name of the class
     * @return data object
     * @throws Exception data not correct, corrupted save
     */
    @Override
    public Object loadData(String filePath, String className) throws Exception {
        // create class object from class name
        Class<?> objclass = Class.forName(className);

        // create object
        Object obj = objclass.getDeclaredConstructor().newInstance();

        // get all attributes and their value
        Field[] fields = objclass.getDeclaredFields();

        // use scanner to read from file
        try (Scanner scanner = new Scanner(new File(filePath))) {
            for (Field field : fields) {
                // get private field
                field.setAccessible(true);

                // read from file
                // if no more line, stop reading
                if (!scanner.hasNextLine()) {
                    break;
                }
                String line = scanner.nextLine();

                // parse safe file
                // set value to object field
                if (line.startsWith("{") && line.endsWith("}") && field.getType() == Map.class) {
                    Map<String, String> map = Arrays.stream(line.substring(1, line.length() - 1).split(", "))
                            .filter(entry -> entry.contains("="))
                            .map(entry -> entry.split("="))
                            .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
                    field.set(obj, map);

                } else if (line.startsWith("[") && line.endsWith("]") && field.getType() == List.class) {
                    List<String> list = Arrays.asList(line.substring(1, line.length() - 1).split(", "));
                    field.set(obj, list);

                } else if (line.startsWith("[") && line.endsWith("]") && field.getType().isArray()) {
                    String[] array = line.substring(1, line.length() - 1).split(", ");
                    field.set(obj, array);

                } else if (field.getType() == int.class) {
                    field.setInt(obj, Integer.parseInt(line));

                } else if (field.getType() == double.class) {
                    field.setDouble(obj, Double.parseDouble(line));

                } else if (field.getType() == boolean.class) {
                    field.setBoolean(obj, Boolean.parseBoolean(line));

                } else {
                    field.set(obj, line);
                }
            }
        }
        return obj;
    }
}