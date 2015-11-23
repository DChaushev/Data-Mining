package com.fmi.dm.hw06;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Dimitar
 */
public class ItemsCollection {
    
    List<Item> items;
    
    public ItemsCollection() {
        items = new ArrayList<>();
    }
    
    public ItemsCollection(List<Item> items) {
        this.items = items;
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public void removeItem(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (item.equals(items.get(i))) {
                items.remove(i);
                break;
            }
        }
    }
    
    public List<Item> getKNearest(int k, Item item) {
        Queue<Item> pq = new PriorityQueue<>(k, (x, y) -> {
            double result = x.distanceTo(item) - y.distanceTo(item);
            if (result > 0) {
                return 1;
            }
            if (result < 0) {
                return -1;
            }
            return 0;
        });
        
        for (Item i : items) {
            pq.add(i);
        }
        
        return new ArrayList<>(pq).subList(0, k);
    }
    
    @Override
    public String toString() {
        return items.toString();
    }
}
