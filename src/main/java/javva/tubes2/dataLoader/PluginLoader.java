package javva.tubes2.dataLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.lang.reflect.Method;


/**
 * Plugin Loader <br>
 * Load plugins from .jar file <br>
 * <p>
 * Class loaded must be an implementation of DataLoader Interface
 */
public class PluginLoader {

    /**
     * Load dataloader plugins from jar file
     *
     * @param jarPath relative path to jar file
     */
    public void loadPlugins(String jarPath) throws Exception {
        try {
            // load plugins
            List<?> classesLoaded = PluginLoader.loadClassesFromJar(jarPath);

            for (Object obj : classesLoaded) {
                Class<?> dataLoaderClass = (Class<?>) obj;
                DataLoader dataLoader = (DataLoader) dataLoaderClass.getDeclaredConstructor().newInstance();
                SaveManager saveManager = SaveManager.getInstance();
                saveManager.addSaveFormat(dataLoader.getFileFormat(), dataLoaderClass);
            }


        } catch (Exception e) {
            throw new Exception("[SaveManager] Failed to load plugins");
        }
    }


    /**
     * Load classes from .jar file <br>
     * Load all class that is an implementation of DataLoader Interface <br>
     * If class is not an implementation of DataLoader, it will be ignored <br>
     *
     * @param jarFilePath relative path from root of project to .jar file
     * @return list of DataLoader classes
     * @throws Exception if file not found, invalid path, or no DataLoader found
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
                } catch (Exception e) {
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
            Class<?> dataLoaderInterface = classLoader.loadClass("javva.tubes2.dataLoader.DataLoader");

            // Check if the loaded class implements the DataLoader interface
            List<Class<?>> interfaces = Stream.of(loadedClass.getInterfaces()).toList();
            if (!interfaces.contains(dataLoaderInterface)) {
                return false;
            }

            // Get all methods from the DataLoader interface
            List<Method> interfaceMethods = Stream.of(dataLoaderInterface.getDeclaredMethods()).toList();

            // Get all methods from the implementing class
            List<Method> classMethods = Stream.of(loadedClass.getDeclaredMethods()).toList();
            Set<String> classMethodSignatures = classMethods.stream()
                    .map(PluginLoader::getMethodSignature)
                    .collect(Collectors.toSet());

            // Check if all interface methods are implemented in the class
            for (Method method : interfaceMethods) {
                if (!classMethodSignatures.contains(getMethodSignature(method))) {
                    return false;
                }
            }

            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Get the method signature <br>
     * Method signature is the method name and its parameter types <br>
     *
     * @param method the method
     * @return the method signature
     */
    private static String getMethodSignature(Method method) {
        return method.getName() + Stream.of(method.getParameterTypes()).map(Class::getName).collect(Collectors.joining(","));
    }
}
