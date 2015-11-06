package com.fmi.dm.hw3;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Dimitar
 */
public class NQueens {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        QueensBoard b = new QueensBoard(n);
        System.out.println(b);
        b.solve();
        b.printBoard();

//        System.out.println(Arrays.toString(MagicSolver.doYourMagic(n)));
    }

}
