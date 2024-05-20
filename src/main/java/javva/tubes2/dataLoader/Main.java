package javva.tubes2.dataLoader;

import plugin.JSONDataLoader;
import plugin.YAMLDataLoader;

public class Main {
    public static void main(String[] args) throws Exception {
        Test test = new Test(1, 2, 3, 4, 5);

        // TEST JSON SAVE LOAD
        // use save
        DataLoader dataPlugin = new JSONDataLoader();
        dataPlugin.saveData(test, "temp.json");

        // use load
        Test test2 = (Test) dataPlugin.loadData("temp.json", Test.class.getName());
        test2.printTest();

        // TEST XLM SAVE LOAD
        // use save
        dataPlugin = new YAMLDataLoader();
        dataPlugin.saveData(test, "temp.yaml");

        // use load
        Test test3 = (Test) dataPlugin.loadData("temp.yaml", Test.class.getName());
        test3.printTest();
    }
}