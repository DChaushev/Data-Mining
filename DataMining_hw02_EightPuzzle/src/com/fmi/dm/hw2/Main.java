package com.fmi.dm.hw2;

/**
 *
 * @author Dimitar
 */
public class Main {

    public static void main(String[] args) {
        EightPuzzleSolver eps = new EightPuzzleSolver();
        
        System.out.println("Number of moves: " + eps.solveFor(new int[]{0, 3, 8, 4, 1, 7, 2, 6, 5}) + "\n------------------\n");
        System.out.println("Number of moves: " + eps.solveFor(new int[]{8, 4, 7, 5, 0, 6, 2, 1, 3}) + "\n------------------\n");
        System.out.println("Number of moves: " + eps.solveFor(new int[]{2, 8, 1, 6, 7, 4, 3, 5, 0}) + "\n------------------\n");
        System.out.println("Number of moves: " + eps.solveFor(new int[]{0, 7, 5, 3, 4, 8, 1, 6, 2}) + "\n------------------\n");
//        System.out.println("Number of moves: " + eps.solveFor(new int[]{13, 7, 13, 14, 15, 8, 9, 11, 5, 2, 0, 10, 4, 5, 1, 6}) + "\n------------------\n");
    }
}
