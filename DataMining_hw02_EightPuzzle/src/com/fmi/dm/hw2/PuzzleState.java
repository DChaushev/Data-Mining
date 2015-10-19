package com.fmi.dm.hw2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Dimitar
 */
public final class PuzzleState implements Comparable<PuzzleState> {

    //TODO: remove all 3's from the code and make it generic(to work with NxN puzzle)
    private static final int[] NEIGHBOURS = new int[]{-3, 3, -1, 1};

    private static int[] finalState = null;

    public final PuzzleState parent;
    public final int[] board;
    public final int depth;
    public final int heuristicIndex;

    public PuzzleState(int[] board, PuzzleState parent) {
        this.board = board;
        if (finalState == null) {
            setFinalState();
        }
        this.parent = parent;
        this.depth = parent == null ? 0 : parent.depth + 1;
        this.heuristicIndex = calculateHeuristicIndex();
    }

    public List<PuzzleState> getPossibleMoves() {
        List<PuzzleState> possibleMoves = new LinkedList<>();

        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i] == 0) {
                for (int neighbour : NEIGHBOURS) {

                    //TODO: this is a quick fix
                    if (i % 3 == 0 && neighbour == -1) {
                        continue;
                    }

                    if ((i - 2) % 3 == 0 && neighbour == 1) {
                        continue;
                    }

                    int x = i + neighbour;
                    if (x >= 0 && x < this.board.length) {
                        int[] nextState = new int[this.board.length];
                        System.arraycopy(this.board, 0, nextState, 0, this.board.length);
                        nextState[i] = nextState[x];
                        nextState[x] = 0;
                        possibleMoves.add(new PuzzleState(nextState, this));
                    }
                }
                break;
            }
        }
        return possibleMoves;
    }

    private int calculateHeuristicIndex() {
        int result = this.depth;

        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i] != finalState[i]) {
                result++;
            }
        }

        return result;
    }

    public boolean isFinal() {
        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i] != finalState[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isSolvable() {
        return getNumberOfInversiont() % 2 == 0;
    }

    private int getNumberOfInversiont() {
        int inversions = 0;
        for (int i = 0; i < this.board.length - 1; i++) {
            for (int j = i + 1; j < this.board.length; j++) {
                if (this.board[i] > this.board[j]) {
                    inversions++;
                }
            }
            if (this.board[i] == 0 && i % 2 == 1) {
                inversions++;
            }
        }
        return inversions;
    }

    void print() {
        for (int i = 0; i < this.board.length; i++) {
            if (i % Math.sqrt(board.length) == 0) {
                System.out.println();
            }
            System.out.print(this.board[i] + " ");
        }
        System.out.println();
    }

    private void setFinalState() {
        System.out.println("Setting final state...");
        PuzzleState.finalState = new int[this.board.length];
        for (int i = 0; i < this.board.length; i++) {
            PuzzleState.finalState[i] = i;
        }
    }

    @Override
    public int compareTo(PuzzleState o) {
        return this.heuristicIndex - o.heuristicIndex;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Arrays.hashCode(this.board);
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
        final PuzzleState other = (PuzzleState) obj;
        return Arrays.equals(this.board, other.board);
    }
}
