package com.fmi.dm.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Dimitar
 */
public class QueensBoard {

    private static final Random randomGenerator = new Random();

    private static final boolean INCREMENT = true;
    private static final boolean DICREMENT = false;

    private int[] queens;
    private int[][] conflictsBoard;
    private int maxIterations;

    public QueensBoard(int numberOfQueens) {
        initializeQueens(numberOfQueens);
    }

    public void solve() {
        int iteration = 0;
        while (iteration++ <= maxIterations) {

            if (isSolved()) {
                return;
            }

            int worstQueen = getWorstQueen();
            int bestPosition = getBestPosition(worstQueen);

            updateConflicts(queens[worstQueen], worstQueen, DICREMENT);
            updateConflicts(queens[bestPosition], bestPosition, INCREMENT);
        }
        initializeQueens(queens.length);
        solve();
    }

    private int getWorstQueen() {
        List<Integer> equalConflicts = new ArrayList<>(queens.length);

        int maxx = 1;
        for (int i = 0; i < queens.length; i++) {
            int conflicts = conflictsBoard[queens[i]][i];
            if (conflicts == maxx) {
                equalConflicts.add(i);
            } else if (conflicts > maxx) {
                maxx = conflicts;
                equalConflicts.clear();
                equalConflicts.add(i);
            }
        }

        return equalConflicts.get(randomGenerator.nextInt(equalConflicts.size()));
    }

    private int getBestPosition(int worstQueen) {
        List<Integer> equalBests = new ArrayList<>(queens.length);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < queens.length; i++) {
            if (i != worstQueen) {
                int conflicts = conflictsBoard[i][worstQueen];
                if (conflicts == min) {
                    equalBests.add(i);
                } else if (conflicts < min) {
                    min = conflicts;
                    equalBests.clear();
                    equalBests.add(i);
                }
            }
        }

        return equalBests.get(randomGenerator.nextInt(equalBests.size()));
    }

    public void initializeQueens(int numberOfQueens) {
        this.maxIterations = numberOfQueens * 2;
        this.queens = randomGenerator.ints(0, numberOfQueens).distinct().limit(numberOfQueens).toArray();
        this.conflictsBoard = new int[queens.length][queens.length];

        for (int i = 0; i < queens.length; i++) {
            int x = queens[i];
            int y = i;

            updateConflicts(x, y, INCREMENT);
        }
    }

    private void updateField(int x, int y, boolean isIncrementing) {
        if (isIncrementing) {
            conflictsBoard[x][y]++;
        } else {
            conflictsBoard[x][y]--;
        }
    }

    private boolean isGood(int x) {
        return x >= 0 && x < queens.length;
    }

    private void updateSequence(int x, int y, int increaseX, int increaseY, boolean isIncrementing) {
        x += increaseX;
        y += increaseY;
        while (isGood(x) && isGood(y)) {
            updateField(x, y, isIncrementing);
            x += increaseX;
            y += increaseY;
        }
    }

    private void updateConflicts(int x, int y, boolean isIncrementing) {
        //update row
        updateSequence(x, -1, 0, 1, isIncrementing);
        // update upper left diagonal
        updateSequence(x, y, -1, -1, isIncrementing);
        // update lower left diagonal
        updateSequence(x, y, 1, -1, isIncrementing);
        //update upper right diagonal
        updateSequence(x, y, -1, 1, isIncrementing);
        //update lower right diagonal
        updateSequence(x, y, 1, 1, isIncrementing);
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

    public void printConflicts() {
        for (int i = 0; i < conflictsBoard.length; i++) {
            for (int j = 0; j < conflictsBoard[i].length; j++) {
                System.out.print(conflictsBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isSolved() {
        for (int i = 0; i < queens.length; i++) {
            if (conflictsBoard[queens[i]][i] != 1) {
                return false;
            }
        }
        return true;
    }
}
