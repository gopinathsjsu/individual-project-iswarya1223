package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Singleton Design Pattern Implementation
 */
public class CapCategory {
    private static CapCategory capCategory = null;
    //static HashMap<String, items> items = new HashMap<String, items>();
    public static HashMap<String,Integer> capInfo = new HashMap<String,Integer>();
    private CapCategory() {
        capInfo.put("Essentials",3);
        capInfo.put("Luxury",4);
        capInfo.put("Misc",6);

    }

    public static CapCategory getInstance() {
        if (capCategory != null) {
            return capCategory;
        }
        else {
            capCategory = new CapCategory();
            return capCategory;
        }
    }

}