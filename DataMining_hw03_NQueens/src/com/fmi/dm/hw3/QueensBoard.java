package com.fmi.dm.hw3;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Dimitar
 */
public class QueensBoard {

    private static final Random randomGenerator = new Random();

    private int[] queens;
    private int maxIterations;

    public QueensBoard(int numberOfQueens) {
        initializeQueens(numberOfQueens);
    }

    public void solve() {
        int[] candidates = new int[queens.length];
        int numberOfQueensWithMostConflicts;

        int iteration = 0;
        while (iteration++ <= maxIterations) {

            int maxConflicts = 0;
            numberOfQueensWithMostConflicts = 0;

            for (int i = 0; i < queens.length; i++) {
                int conflicts = getNumberOfConflictsAt(queens[i], i);
                if (conflicts == maxConflicts) {
                    candidates[numberOfQueensWithMostConflicts++] = i;
                } else if (conflicts > maxConflicts) {
                    maxConflicts = conflicts;
                    numberOfQueensWithMostConflicts = 0;
                    candidates[numberOfQueensWithMostConflicts++] = i;
                }
            }

            if (maxConflicts == 0) {
                return;
            }

            int oldCol = candidates[randomGenerator.nextInt(numberOfQueensWithMostConflicts)];

            int minConflicts = queens.length;
            int numberOfPossibleMovingRows = 0;

            for (int i = 0; i < queens.length; i++) {
                int conflicts = getNumberOfConflictsAt(i, oldCol);
                if (conflicts == minConflicts) {
                    candidates[numberOfPossibleMovingRows++] = i;
                } else if (conflicts < minConflicts) {
                    minConflicts = conflicts;
                    numberOfPossibleMovingRows = 0;
                    candidates[numberOfPossibleMovingRows++] = i;
                }
            }

            if (numberOfPossibleMovingRows != 0) {
                queens[oldCol] = candidates[randomGenerator.nextInt(numberOfPossibleMovingRows)];
            }
        }

        initializeQueens(queens.length);
        solve();
    }

    private int getNumberOfConflictsAt(int row, int col) {
        int numberOfConflicts = 0;
        for (int i = 0; i < queens.length; i++) {
            if (i == col) {
                continue;
            }
            int x = queens[i];
            if (x == row || Math.abs(x - row) == Math.abs(i - col)) {
                numberOfConflicts++;
            }
        }
        return numberOfConflicts;
    }

    public void initializeQueens(int numberOfQueens) {
        this.maxIterations = numberOfQueens * 2;
        this.queens = randomGenerator.ints(0, numberOfQueens).distinct().limit(numberOfQueens).toArray();
    }

    @Override
    public String toString() {
        return Arrays.toString(queens);
    }

    public void printBoard() {
        System.out.println();
        for (int j = 0; j < 4 * queens.length; j++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < queens.length; i++) {
            for (int j = 0; j < queens.length; j++) {
                System.out.print(queens[j] == i ? "| Q " : "|   ");
                if (j == queens.length - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            for (int j = 0; j < queens.length; j++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

}
