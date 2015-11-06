package com.fmi.dm.hw3;

/**
 *
 * @author Dimitar
 */
public class MagicSolver {

    public static int[] doYourMagic(int n) {
        int[] board = new int[n];

        if (n % 6 != 2 && n % 6 != 3) {
            int x = 2;
            int y = 1;
            for (int i = 0; i < n; i++) {
                if (i < n / 2) {
                    board[i] = x;
                    x += 2;
                } else {
                    board[i] = y;
                    y += 2;
                }
            }
        } else {
            int[] even = new int[n / 2];
            int[] odd = new int[n - even.length];

            if (n % 6 == 2) {
                odd[0] = 3;
                odd[1] = 1;
                int x = 7;
                for (int i = 2; i < odd.length - 1; i++) {
                    odd[i] = x;
                    x += 2;
                }
                odd[odd.length - 1] = 5;

                x = 2;
                for (int i = 0; i < n / 2; i++) {
                    even[i] = x;
                    x += 2;
                }
            } else if (n % 6 == 3) {
                int x = 4;
                for (int i = 0; i < n / 2 - 1; i++) {
                    even[i] = x;
                    x += 2;
                }
                even[even.length - 1] = 2;

                x = 5;
                for (int i = 0; i < n / 2 - 2; i++) {
                    odd[i] = x;
                    x += 2;
                }
                even[even.length - 2] = 1;
                even[even.length - 1] = 3;

            }
            System.arraycopy(even, 0, board, 0, even.length);
            System.arraycopy(odd, 0, board, even.length, odd.length);
        }

        return board;
    }
}
