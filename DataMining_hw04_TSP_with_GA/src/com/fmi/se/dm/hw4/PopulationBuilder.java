package com.fmi.se.dm.hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Dimitar
 */
public class PopulationBuilder {

    private static final Random rand = new Random();

    public static Population initPopulation(int N) {
        List<Tour> population = new ArrayList<>(2 * N);
        List<City> l = new ArrayList<>(N);
        Set<City> used = new HashSet<>();

        for (int i = 0; i < N; i++) {
            City c = generateRandomCity(N * 10);
            while (used.contains(c)) {
                c = generateRandomCity(N * 10);
            }
            used.add(c);
            l.add(c);
        }

        for (int i = 0; i < N * 2; i++) {
            Collections.shuffle(l);
            population.add(new Tour(new ArrayList<>(l)));
        }

        System.out.println("Population initialized...");

        return new Population(population);
    }

    public static City generateRandomCity(int boundaries) {
        return new City(rand.nextInt(boundaries), rand.nextInt(boundaries));
    }

}
