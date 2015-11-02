package com.fmi.dm.hw3;

/**
 *
 * @author Dimitar
 */
public class NQueens {

    public static void main(String[] args) {
        QueensBoard b = new QueensBoard(8);
        System.out.println(b);
        b.solve();
        b.printBoard();
    }

}
