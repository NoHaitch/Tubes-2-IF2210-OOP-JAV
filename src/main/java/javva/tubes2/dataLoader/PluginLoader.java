package javva.tubes2.dataLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.List;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Plugin Loader <br>
 * Load plugins from .jar file <br><br>
 *
 * Class loaded must be an implementation of DataLoader Interface
 */
public class PluginLoader {
    /**
     * Load classes from .jar file <br>
     * Load all class that is an implementation of DataLoader Interface <br>
     * If class is not an implementation of DataLoader, it will be ignored <br>
     *
     * @param jarFilePath relative path from root of project to .jar file
     * @return list of DataLoader classes
     * @exception Exception if file not found, invalid path, or no DataLoader found
     */
    public static List<Class<?>> loadClassesFromJar(String jarFilePath) throws Exception {
        List<Class<?>> loadedClasses = new ArrayList<>();
        try {
            // Convert the file path to a URL
            File file = new File(jarFilePath);
            URL url = file.toURI().toURL();

            // Load the class using ClassLoader
            try (URLClassLoader classLoader = new URLClassLoader(new URL[]{url})) {

                // Open the JAR file
                try (JarFile jarFile = new JarFile(file)) {

                    // Get all entries in the JAR file
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String entryName = entry.getName();

                        // Check if the entry is a class file
                        if (entryName.endsWith(".class")) {
                            // get class name that is valid
                            String className = entryName.replace('/', '.').replace(".class", "");
                            if (className.contains(".")) {
                                className = className.substring(className.indexOf(".") + 1);
                            }

                            // Check if class is an implementation of DataLoader
                            if (isDataloaderImplementation(classLoader, className)) {
                                try {
                                    // Load the class
                                    Class<?> loadedClass = classLoader.loadClass(className);
                                    loadedClasses.add(loadedClass);
                                    System.out.println("[PluginLoader] Loaded class: " + loadedClass.getName());

                                } catch (ClassNotFoundException e) {
                                    System.err.println("[PluginLoader] Class not found: " + className);
                                }

                            } else {
                                System.out.println("[PluginLoader] class " + className + " is not an implementation of DataLoader");
                            }
                        }
                    }
                } catch (Exception e){
                    throw new Exception("Failed to open jar file");
                }
            }
        } catch (IOException e) {
            throw new Exception("File not found or invalid path");
        }

        if (loadedClasses.isEmpty()) {
            throw new Exception("No DataLoader Found in Jar File");
        }
        return loadedClasses;
    }

    /**
     * Check if class implements DataLoader interface <br>
     *
     * @param className the name of the class
     * @return true if valid, false otherwise
     */
    private static boolean isDataloaderImplementation(ClassLoader classLoader, String className) {
        try {
            Class<?> loadedClass = classLoader.loadClass(className);

            // Check if the loaded class implements the DataLoader interface
            for (Class<?> iface : loadedClass.getInterfaces()) {
                if (iface.getName().equals("javva.tubes2.dataLoader.DataLoader")) {
                    return true;
                }
            }
        } catch (ClassNotFoundException e) {
            return false;
        }

        return false;
    }
}
