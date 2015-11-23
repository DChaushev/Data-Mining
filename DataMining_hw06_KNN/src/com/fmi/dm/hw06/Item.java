package com.fmi.dm.hw06;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Dimitar
 */
public class Item {

    public static final Map<String, Integer> FEATURE_NAME_TO_ID_MAP;
    public static final Map<Integer, String> ID_TO_FEATURE_NAME_MAP;

    public static final Map<Integer, String> CLASS_TO_NAME_MAP;
    public static final Map<String, Integer> NAME_TO_CLASS_MAP;

    static {
        FEATURE_NAME_TO_ID_MAP = new HashMap<>();
        FEATURE_NAME_TO_ID_MAP.put("sepallength", 1);
        FEATURE_NAME_TO_ID_MAP.put("sepalwidth", 2);
        FEATURE_NAME_TO_ID_MAP.put("petallength", 3);
        FEATURE_NAME_TO_ID_MAP.put("petalwidth", 4);

        ID_TO_FEATURE_NAME_MAP = new HashMap<>();
        ID_TO_FEATURE_NAME_MAP.put(1, "sepallength");
        ID_TO_FEATURE_NAME_MAP.put(2, "sepalwidth");
        ID_TO_FEATURE_NAME_MAP.put(3, "petallength");
        ID_TO_FEATURE_NAME_MAP.put(4, "petalwidth");

        CLASS_TO_NAME_MAP = new HashMap<>();
        CLASS_TO_NAME_MAP.put(1, "Iris-setosa");
        CLASS_TO_NAME_MAP.put(2, "Iris-versicolor");
        CLASS_TO_NAME_MAP.put(3, "Iris-virginica");

        NAME_TO_CLASS_MAP = new HashMap<>();
        NAME_TO_CLASS_MAP.put("Iris-setosa", 1);
        NAME_TO_CLASS_MAP.put("Iris-versicolor", 2);
        NAME_TO_CLASS_MAP.put("Iris-virginica", 3);
    }

    private int label;
    Map<Integer, Double> features;

    public Item() {
        features = new HashMap<>();
    }

    public Item(double args[]) {
        this();
        for (int i = 0; i < args.length; i++) {
            features.put(i + 1, args[i]);
        }
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public Map<Integer, Double> getFeatures() {
        return features;
    }

    public void setFeatures(Map<Integer, Double> features) {
        this.features = features;
    }

    public double distanceTo(Item other) {
        if (this.features.size() != other.features.size()) {
            throw new IllegalArgumentException("The two items have different number of attributes. Cannot calculate the distance!");
        }

        double[] thisValues = new double[this.features.size()];
        double[] otherValues = new double[this.features.size()];

        for (int i = 0; i < this.features.size(); i++) {
            thisValues[i] = this.features.get(i + 1);
            otherValues[i] = other.features.get(i + 1);
        }

        double sum = 0;

        for (int i = 0; i < thisValues.length; i++) {
            sum += ((thisValues[i] - otherValues[i]) * (thisValues[i] - otherValues[i]));
        }

        return Math.sqrt(sum);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.features);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        return Objects.equals(this.features, other.features);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("{ ");
        result.append("class: ").append(CLASS_TO_NAME_MAP.get(label));

        for (Map.Entry<Integer, Double> entrySet : features.entrySet()) {
            Integer key = entrySet.getKey();
            Double value = entrySet.getValue();

            result.append(", ").append(ID_TO_FEATURE_NAME_MAP.get(key)).append(": ").append(value);
        }

        result.append(" }");

        return result.toString();
    }
}
