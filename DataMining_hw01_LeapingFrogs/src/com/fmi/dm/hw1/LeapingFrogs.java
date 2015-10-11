package com.fmi.dm.hw1;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Dimitar
 */
public final class LeapingFrogs {

    private final char FROG_FACING_RIGHT = 'r';
    private final char FROG_FACING_LEFT = 'l';
    private final char EMPTY_SPACE = 'e';

    private int frogsPerSide;
    private GameState startingState;
    private GameState winningState;

    public LeapingFrogs(int frogsPerSide) {
        this.setFrogsPerSide(frogsPerSide);
    }

    public void solve() {
        System.out.println("Starting positions: \t" + this.startingState);
        System.out.println("Desired positions: \t" + this.winningState);

        Stack<GameState> stack = new Stack<>();

        stack.push(startingState);
        int length;
        GameState finalState = null;

        while (!stack.isEmpty()) {
            GameState state = stack.pop();

            if (state.equals(winningState)) {
                finalState = state;
                break;
            }

            if (state == startingState) {
                length = state.board.length / 2;
            } else {
                length = state.board.length;
            }

            for (int i = 0; i < length; i++) {
                validateAndApplyMove(state, i);
            }

            for (GameState child : state.children) {
                stack.push(child);
            }
        }

        GameState state = finalState;
        List<GameState> states = new LinkedList<>();

        while (state != null) {
            states.add(0, state);
            state = state.parent;
        }

        System.out.println("Moves made: " + (states.size() - 1));
        for (GameState rightState : states) {
            System.out.println(rightState);
        }
        System.out.println();

    }

    public void validateAndApplyMove(GameState state, int position) {
        char currentFrog = state.board[position];

        if (currentFrog == FROG_FACING_RIGHT) {
            if (state.board.length > position + 1 && state.board[position + 1] == EMPTY_SPACE) {
                applyMove(state, position, position + 1);
            }
            if (state.board.length > position + 2 && state.board[position + 2] == EMPTY_SPACE) {
                applyMove(state, position, position + 2);
            }
        } else if (currentFrog == FROG_FACING_LEFT) {
            if (position - 1 >= 0 && state.board[position - 1] == EMPTY_SPACE) {
                applyMove(state, position, position - 1);
            }
            if (position - 2 >= 0 && state.board[position - 2] == EMPTY_SPACE) {
                applyMove(state, position, position - 2);
            }
        }
    }

    private void applyMove(GameState state, int oldPosition, int newPosition) {
        //System.out.print(String.format("Moving %d to %d --> ", oldPosition, newPosition));

        char[] newStateBoard = new char[state.board.length];
        System.arraycopy(state.board, 0, newStateBoard, 0, newStateBoard.length);
        newStateBoard[newPosition] = newStateBoard[oldPosition];
        newStateBoard[oldPosition] = EMPTY_SPACE;
        GameState newGameState = new GameState(newStateBoard, state);
        state.children.add(newGameState);

        //System.out.println(newGameState);
    }

    public int getFrogsPerSide() {
        return frogsPerSide;
    }

    public void setFrogsPerSide(int frogsPerSide) {
        this.frogsPerSide = frogsPerSide;
        char[] startingBoard = new char[frogsPerSide * 2 + 1];
        char[] winningBoard = new char[frogsPerSide * 2 + 1];

        for (int i = 0; i < frogsPerSide; i++) {
            startingBoard[i] = FROG_FACING_RIGHT;
            winningBoard[i] = FROG_FACING_LEFT;
        }

        startingBoard[frogsPerSide] = EMPTY_SPACE;
        winningBoard[frogsPerSide] = EMPTY_SPACE;

        for (int i = frogsPerSide + 1; i < frogsPerSide * 2 + 1; i++) {
            startingBoard[i] = FROG_FACING_LEFT;
            winningBoard[i] = FROG_FACING_RIGHT;
        }

        this.startingState = new GameState(startingBoard);
        this.winningState = new GameState(winningBoard);
    }
}
