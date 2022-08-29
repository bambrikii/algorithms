package org.bambrikii.examples.martix;

/**
 * https://www.youtube.com/watch?v=0oJyNmEbS4w
 * https://www.geeksforgeeks.org/strassens-matrix-multiplication/
 */
public class StrassenMatrixMultiplication {
    private void validateB(int[][] b) {
        if (b.length != b[0].length) {
            throw new IllegalArgumentException("Squared matrix #2 expected");
        }
    }

    private void validateA(int[][] a) {
        if (a.length != a[0].length) {
            throw new IllegalArgumentException("Squared matrix #1 expected");
        }
    }

    public int[][] multiply(int[][] a, int[][] b) {
        validateA(a);
        validateB(b);

        int n = a.length;
        int[][] res = multiply(a, b, n);
        return res;
    }

    private int[][] multiply(int[][] a, int[][] b, int n) {
        if (n == 1) {
            int val = a[0][0] * b[0][0];
            return new int[][]{{val}};
        }

        if (n == 2) {
            return multiply2x2(a, b);
        }

        int d = n / 2;

        int[][] a00 = new int[d][d];
        int[][] a01 = new int[d][d];
        int[][] a10 = new int[d][d];
        int[][] a11 = new int[d][d];

        int[][] b00 = new int[d][d];
        int[][] b01 = new int[d][d];
        int[][] b10 = new int[d][d];
        int[][] b11 = new int[d][d];

        // TODO: is this logic correct?
        split(a, b, d, a00, a01, a10, a11, b00, b01, b10, b11);

        int[][] res00 = add(multiply(a00, b00), multiply(a01, b10));
        int[][] res01 = add(multiply(a00, b01), multiply(a01, b11));
        int[][] res10 = add(multiply(a10, b00), multiply(a11, b10));
        int[][] res11 = add(multiply(a10, b01), multiply(a11, b11));

        // TODO: is this logic correct?
        int[][] res = join(res00, res01, res10, res11);

        return res;
    }

    private static void split(int[][] a, int[][] b, int d, int[][] a00, int[][] a01, int[][] a10, int[][] a11, int[][] b00, int[][] b01, int[][] b10, int[][] b11) {
        for (int x = 0; x < d; x++) {
            for (int y = 0; y < d; y++) {
                a00[x][y] = a[x][y];
                a01[x][y] = a[x][y + d];
                a10[x][y] = a[d + x][y];
                a11[x][y] = a[x + d][y + d];
                b00[x][y] = b[x][y];
                b01[x][y] = b[x][y + d];
                b10[x][y] = b[d + x][y];
                b11[x][y] = b[x + d][y + d];
            }
        }
    }

    private int[][] join(int[][] res00, int[][] res01, int[][] res10, int[][] res11) {
        int d = res00.length;
        int n = d * 2;
        int[][] res = new int[n][n];
        for (int x = 0; x < d; x++) {
            for (int y = 0; y < d; y++) {
                res[x][y] = res00[x][y];
                res[x][y + d] = res01[x][y];
                res[x + d][y] = res10[x][y];
                res[x + d][y + d] = res11[x][y];
            }
        }
        return res;
    }

    private int[][] multiply2x2(int[][] a, int[][] b) {
        return new int[][]{
                {
                        a[0][0] * b[0][0] + a[0][1] * b[1][0],
                        a[1][0] * b[1][0] + a[1][1] * b[1][1]
                },
                {
                        a[0][0] * b[1][0] + a[0][1] * b[1][1],
                        a[1][0] * b[1][0] + a[1][1] * b[1][1]
                }
        };
    }

    private int[][] add(int[][] a, int[][] b) {
        int n = a.length;
        int[][] res = new int[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                res[x][y] = a[x][y] + b[x][y];
            }
        }
        return res;
    }
}
