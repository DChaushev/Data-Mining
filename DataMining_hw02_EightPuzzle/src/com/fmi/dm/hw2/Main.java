package com.fmi.dm.hw2;

/**
 *
 * @author Dimitar
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Number of moves: " + EightPuzzleSolver.solveFor(new int[]{0, 3, 8, 4, 1, 7, 2, 6, 5}) + "\n------------------\n");
        System.out.println("Number of moves: " + EightPuzzleSolver.solveFor(new int[]{8, 4, 7, 5, 0, 6, 2, 1, 3}) + "\n------------------\n");
        System.out.println("Number of moves: " + EightPuzzleSolver.solveFor(new int[]{2, 8, 1, 6, 7, 4, 3, 5, 0}) + "\n------------------\n");
    }
}
