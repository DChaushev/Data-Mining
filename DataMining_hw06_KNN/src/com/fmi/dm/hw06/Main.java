package com.fmi.dm.hw06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author Dimitar
 */
public class Main {

    private final static String PATH_TO_FILE = "C:/Program Files/Weka-3-6/data/iris.arff";

    public static void main(String[] args) throws Exception {
        ItemsCollection collection = initializeCollection(PATH_TO_FILE);

        int all = collection.items.size();
        double guessed = 0;

        for (Item item : collection.items) {
            ItemsCollection subCollection = new ItemsCollection(new ArrayList<>(collection.items));
            subCollection.removeItem(item);

            List<Item> kNearest = subCollection.getKNearest(4, item);
            int clazz = getClass(kNearest);

            if (clazz == item.getLabel()) {
                guessed++;
            }
        }

        System.out.println("Guessed: " + guessed);
        System.out.println("All: " + all);
        System.out.println((double) (guessed / all) * 100 + "%");

    }

    private static ItemsCollection initializeCollection(String file) throws Exception {

        DataSource source = new DataSource(file);
        Instances data = source.getDataSet();
        ItemsCollection collection = new ItemsCollection();

        for (int i = 0; i < data.numInstances(); i++) {
            Instance instance = data.instance(i);

            Item item = new Item();
            item.setLabel(Item.NAME_TO_CLASS_MAP.get(instance.stringValue(4)));
            for (int j = 0; j < 4; j++) {
                item.features.put(Item.FEATURE_NAME_TO_ID_MAP.get(instance.attribute(j).name()), instance.value(j));
            }

            collection.addItem(item);
        }
        return collection;
    }

    private static int getClass(List<Item> items) {
        Map<Integer, Integer> map = new HashMap<>();

        for (Item item : items) {
            int label = item.getLabel();
            if (map.containsKey(label)) {
                map.put(label, map.get(label) + 1);
            } else {
                map.put(label, 1);
            }
        }
        int maxx = 0;

        for (Item item : items) {
            int label = item.getLabel();
            if (map.get(label) > maxx) {
                maxx = label;
            }
        }

        return maxx;
    }
}
