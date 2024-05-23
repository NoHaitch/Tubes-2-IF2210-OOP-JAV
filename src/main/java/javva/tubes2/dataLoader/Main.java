package javva.tubes2.dataLoader;

import java.util.List;

import javva.tubes2.Player;


public class Main {
    public static void main(String[] args) throws Exception {
//        String jarFilePath = "./JSONDataLoader.jar";
//        String className = "plugin.JSONDataLoader";
//        PluginLoader.readClassFromJar(jarFilePath, className);
//
//        Test test = new Test(1, 2, 3, 4, 5);

//        String jarFilePath = "./JSONDataLoader.jar";
//        PluginLoader.loadClassesFromJar(jarFilePath);


//        List<Class<?>> loadedClasses = PluginLoader.loadClassesFromJar("./JSONDataLoader.jar");
//        System.out.println("Loaded classes:");
//        for (Class<?> clazz : loadedClasses) {
//            System.out.println(clazz.getName());
//        }

        SaveManager saveManager = SaveManager.getInstance();

        Player player1 = new Player(1000);

        Player player2 = new Player(2000);

        saveManager.saveGame(player1, player2, "result", "txt");

        saveManager.loadPlayer("result", "player1", "txt");
        saveManager.loadPlayer("result", "player2", "txt");
        saveManager.loadGameState("result", "txt");

    }
}