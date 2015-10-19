package com.fmi.dm.hw2;

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

    public static int solveFor(int[] array) {
        PuzzleState startingState = new PuzzleState(array, null);

        if (startingState.isSolvable()) {
            Queue<PuzzleState> queue = new PriorityQueue<>();
            Set<PuzzleState> used = new HashSet<>();

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

            return -1;
        } else {
            return -1;
        }
    }
}
