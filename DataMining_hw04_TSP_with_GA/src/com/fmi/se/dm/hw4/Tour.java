package com.fmi.se.dm.hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Dimitar
 */
public class Tour implements Comparable<Tour> {

    private List<City> tour;
    private double distance;

    public Tour() {
        tour = new ArrayList<>();
    }

    public Tour(int size) {
        tour = new ArrayList<>(Collections.nCopies(size, null));
    }

    public Tour(List<City> tour) {
        setTour(tour);
    }

    public int getSize() {
        return tour.size();
    }

    public City getCity(int index) {
        return tour.get(index);
    }

    public void setCity(int index, City city) {
        tour.set(index, city);
    }

    public boolean containsCity(City city) {
        return tour.contains(city);
    }

    public List<City> getTour() {
        return new ArrayList<>(tour);
    }

    public void setTour(List<City> tour) {
        this.tour = new ArrayList<>(tour);
        this.distance = getDistance(this);
    }

    public static double getDistance(Tour t) {
        double distance = 0;
        for (int i = 0; i < t.tour.size() - 1; i++) {
            distance += t.tour.get(i).distanceTo(t.tour.get(i + 1));
        }
        if (t.tour.size() > 1) {
            distance += t.tour.get(t.tour.size() - 1).distanceTo(t.tour.get(0));
        }
        return distance;
    }

    @Override
    public int compareTo(Tour o) {
        double result = getDistance(this) - getDistance(o);
        if (result == 0) {
            return 0;
        }
        return result < 0 ? -1 : 1;
    }

    @Override
    public String toString() {
        return this.tour.toString();
    }
}
