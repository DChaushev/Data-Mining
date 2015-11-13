package com.fmi.se.dm.hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;

/**
 *
 * @author Dimitar
 */
public class Population {

    private static final Random randomGenerator = new Random();

    private List<Tour> population;

    protected Population(List<Tour> population) {
        setPopulation(population);
    }

    protected Population(Population population) {
        setPopulation(population.population);
    }

    public List<Tour> getPopulation() {
        return new ArrayList<>(population);
    }

    public void setPopulation(List<Tour> population) {
        this.population = new ArrayList<>(population);
    }

    public void sort() {
        Collections.sort(population);
    }

    public Population evolvePopulation() {
        int numberOfBestEntities = (int) Math.ceil(0.2 * population.size());
        this.sort();

        List<Tour> nextPopulation = new ArrayList<>(this.population);

        for (int i = 0; i < numberOfBestEntities; i++) {
            Tour parent1 = selectParent(numberOfBestEntities);
            Tour parent2 = selectParent(numberOfBestEntities);

            while (parent2.equals(parent1)) {
                parent2 = selectParent(numberOfBestEntities);
            }

            Tour child = crossover(parent1, parent2);
            nextPopulation.set(nextPopulation.size() - numberOfBestEntities + i, child);
        }

        for (Tour tour : nextPopulation) {
            mutate(tour);
        }

        return new Population(nextPopulation);
    }

    private Tour selectParent(int size) {
        return population.get(randomGenerator.nextInt(size));
    }

    private Tour crossover(Tour parent1, Tour parent2) {
        Tour child = new Tour(parent1.getSize());

        int startPos = (int) (Math.random() * parent1.getSize());
        int endPos = (int) (Math.random() * parent1.getSize());

        for (int i = 0; i < child.getSize(); i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        for (int i = 0; i < parent2.getSize(); i++) {
            if (!child.containsCity(parent2.getCity(i))) {
                for (int ii = 0; ii < child.getSize(); ii++) {
                    if (child.getCity(ii) == null) {
                        child.setCity(ii, parent2.getCity(i));
                        break;
                    }
                }
            }
        }

        return child;
    }

    private void mutate(Tour tour) {
        for (int i = 0; i < tour.getSize(); i++) {
            if (Math.random() < 0.05) {
                int j = (int) (tour.getSize() * Math.random());

                City city1 = tour.getCity(i);
                City city2 = tour.getCity(j);

                tour.setCity(j, city1);
                tour.setCity(i, city2);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
//        for (Tour tour : population) {
//            if (tour != null) {
//                result/*.append(tour.toString())*/.append(" = " + Tour.getDistance(tour) + "\n");
//            } else {
//                result.append("null\n");
//            }
//        }
        OptionalDouble avg = population.stream().mapToDouble(t -> Tour.getDistance(t)).average();
        result.append(" average: " + avg.getAsDouble() + "\n");
        return result.toString();
    }

}
