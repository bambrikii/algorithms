package org.bambrikii.examples.martix;

public class MartixOperations {
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[a.length][];
        for (int aRow = 0; aRow < a.length; aRow++) {
            int[] aRowArr = a[aRow];
            c[aRow] = new int[aRowArr.length];
            for (int aCol = 0; aCol < aRowArr.length; aCol++) {
                int[] bRowArr = b[aCol];
                if (aRowArr.length != b.length) {
                    throw new IllegalArgumentException("a[" + aRow + "].length (" + aRowArr.length + ") != b[][" + aCol
                            + "].length (" + b.length + ")");
                }
                for (int bj = 0; bj < bRowArr.length; bj++) {
                    c[aRow][aCol] = c[aRow][aCol] + a[aRow][aCol] * b[aCol][bj];
                }
            }
        }
        return c;
    }
}
