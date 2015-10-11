package com.fmi.dm.hw1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dimitar
 */
public class GameState {

    public final char[] board;

    public GameState parent;
    public final Set<GameState> children;

    public GameState(char[] board) {
        this(board, null);
    }

    public GameState(char[] board, GameState parent) {
        this.board = board;
        this.parent = parent;
        this.children = new HashSet<>();
    }

    @Override
    public boolean equals(Object obj) {
        GameState other = (GameState) obj;
        if (this.board.length != other.board.length) {
            return false;
        }
        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i] != other.board[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.board);
    }
}
