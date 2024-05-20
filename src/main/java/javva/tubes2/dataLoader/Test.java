package javva.tubes2.dataLoader;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * TEST CLASS FOR DATA LOADER
 */
public class Test {
    private int attribute1;
    private int attribute2;
    private int attribute3;
    public int attribute4;
    public int attribute5;
    public List<Integer> list;
    public Map<String, Integer> map;

    public Test(){
        this.attribute1 = 0;
        this.attribute2 = 0;
        this.attribute3 = 0;
        this.attribute4 = 0;
        this.attribute5 = 0;
        this.list = new java.util.ArrayList<>();
        this.map = new HashMap<>();
    }

    public Test(int attribute1, int attribute2, int attribute3, int attribute4, int attribute5) {
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
        this.attribute3 = attribute3;
        this.attribute4 = attribute4;
        this.attribute5 = attribute5;
        this.list = new java.util.ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        this.map = new HashMap<>();
        for (int i = 0; i < 10; i++){
            map.put("Key" + i, i);
        }
    }

    public void printTest() {
        System.out.println("attribute1 = " + attribute1);
        System.out.println("attribute2 = " + attribute2);
        System.out.println("attribute3 = " + attribute3);
        System.out.println("attribute4 = " + attribute4);
        System.out.println("attribute5 = " + attribute5);
        for (int i = 0; i < 10; i++){
            System.out.println("list[" + i + "] = " + list.get(i));
        }
        for (String key: map.keySet()){
            System.out.println("map[" + key + "] = " + map.get(key));
        }
    }
}
