package com.fmi.dm.hw06;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


/**
 *
 * @author Dimitar
 */
public class Main {

    private final static String PATH_TO_FILE = "C:/Program Files/Weka-3-6/data/iris.arff";

    public static void main(String[] args) {

        ItemsCollection collection = new ItemsCollection();

        try {
            DataSource source = new DataSource(PATH_TO_FILE);
            Instances data = source.getDataSet();

            for (int i = 0; i < data.numInstances(); i++) {
                Instance instance = data.instance(i);

                Item item = new Item();
                item.setLabel(Item.NAME_TO_CLASS_MAP.get(instance.stringValue(4)));
                for (int j = 0; j < 4; j++) {
                    item.features.put(Item.FEATURE_NAME_TO_ID_MAP.get(instance.attribute(j).name()), instance.value(j));
                }

                collection.addItem(item);
            }

            Item newItem = new Item(new double[]{6.3, 3.3, 6.0, 2.5});

            List<Item> kNearest = collection.getKNearest(3, newItem);
            for (Item nearest : kNearest) {
                System.out.println(Item.CLASS_TO_NAME_MAP.get(nearest.getLabel()));
            }

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
