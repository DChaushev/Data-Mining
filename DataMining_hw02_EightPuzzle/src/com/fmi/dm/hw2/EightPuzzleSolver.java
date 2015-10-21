package com.fmi.dm.hw2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Dimitar
 */
public class EightPuzzleSolver {

    public int solveFor(int[] array) {

        if (isSolvable(array)) {
            Queue<PuzzleState> queue = new PriorityQueue<>();
            Set<PuzzleState> used = new HashSet<>();

            int zero = findNull(array);

            PuzzleState startingState = new PuzzleState(array, null, zero);

            queue.add(startingState);

            while (!queue.isEmpty()) {
                PuzzleState state = queue.poll();
                if (!used.contains(state)) {
                    used.add(state);

                    if (state.isFinal()) {
                        // --- print answer --- \\
                        PuzzleState s = state;
                        List<PuzzleState> answer = new LinkedList<>();
                        while (s != null) {
                            answer.add(0, s);
                            s = s.parent;
                        }

                        for (PuzzleState a : answer) {
                            a.print();
                        }
                        // -------------------- \\

                        return state.depth;
                    }

                    queue.addAll(state.getPossibleMoves());
                }
            }

            for (PuzzleState used1 : used) {
                used1.print();
            }
            return -1;
        } else {
            return -1;
        }
    }

    public boolean isSolvable(int[] state) {
        return getNumberOfInversiont(state) % 2 == 0;
    }

    private int getNumberOfInversiont(int[] board) {
        int inversions = 0;
        for (int i = 0; i < board.length - 1; i++) {
            for (int j = i + 1; j < board.length; j++) {
                if (board[i] > board[j]) {
                    inversions++;
                }
            }
            if (board[i] == 0 && i % 2 == 1) {
                inversions++;
            }
        }
        return inversions;
    }

    private int findNull(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    private final class PuzzleState implements Comparable<PuzzleState> {

        private final int[] neighbours;

        public final PuzzleState parent;
        public final int[] board;
        public final int depth;
        public final int heuristicIndex;
        private final int nextRowStep;
        private final int zeroIndex;

        public PuzzleState(int[] board, PuzzleState parent, int zeroIndex) {
            this.board = board;
            this.parent = parent;
            this.depth = parent == null ? 0 : parent.depth + 1;
            this.heuristicIndex = calculateHeuristicIndex();
            this.nextRowStep = (int) Math.sqrt(this.board.length);

            this.zeroIndex = zeroIndex;

            this.neighbours = new int[4];
            this.neighbours[0] = -1;
            this.neighbours[1] = 1;
            this.neighbours[2] = -this.nextRowStep;
            this.neighbours[3] = this.nextRowStep;
        }

        public List<PuzzleState> getPossibleMoves() {
            List<PuzzleState> possibleMoves = new LinkedList<>();

            for (int neighbour : neighbours) {

                if (neighbour == -1 && zeroIndex % this.nextRowStep == 0) {
                    continue;
                }

                if (neighbour == 1 && (zeroIndex - (this.nextRowStep - 1)) % this.nextRowStep == 0) {
                    continue;
                }

                int x = zeroIndex + neighbour;
                if (x >= 0 && x < this.board.length) {
                    int[] nextState = new int[this.board.length];
                    System.arraycopy(this.board, 0, nextState, 0, this.board.length);
                    nextState[zeroIndex] = nextState[x];
                    nextState[x] = 0;
                    possibleMoves.add(new PuzzleState(nextState, this, x));
                }
            }

            return possibleMoves;
        }

        private int calculateHeuristicIndex() {
            int result = this.depth;

            for (int i = 0; i < this.board.length; i++) {
                if (this.board[i] != i) {
                    result++;
                }
            }

            return result;
        }

        public void print() {
            for (int i = 0; i < this.board.length; i++) {
                if (i % Math.sqrt(board.length) == 0) {
                    System.out.println();
                }
                System.out.print(this.board[i] + " ");
            }
            System.out.println();
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

        public boolean isFinal() {
            for (int i = 0; i < this.board.length; i++) {
                if (this.board[i] != i) {
                    return false;
                }
            }
            return true;
        }
    }
}
