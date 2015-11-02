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

        /**
         *
         * I stopped it at 5min for 4x4 puzzle. It seems that Manhattan distance
         * or Hemming's distance are not the best heuristic functions. There's
         * one, called Walking distance, that finds the solution of 4x4 puzzle
         * for milliseconds.
         *
         * For 3x3, the puzzle is solved for about 20 steps, meaning depth of
         * 20. <br>
         *
         * If you run https://n-puzzle-solver.appspot.com/ for random 4x4
         * puzzle, using the pattern database (precalculated), you can see that
         * the # of moves is about 50. <br>
         * For 4x4 puzzle, for depth of 50, there are 657,076,739,307 possible
         * states, according to this site:
         * http://kociemba.org/fifteen/fifteensolver.html
         */
//        System.out.println("Number of moves: " + eps.solveFor(new int[]{13, 7, 13, 14, 15, 8, 9, 11, 5, 2, 0, 10, 4, 5, 1, 6}) + "\n------------------\n");
    }
}
