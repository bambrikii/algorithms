package org.bambrikii.examples.matrix;

import org.bambrikii.examples.martix.StrassenMatrixMultiplication;
import org.junit.jupiter.api.Test;

public class StrassenMatrixMultiplicationTest {
    @Test
    public void shouldMultiply() {
        int[][] a = {{1, 1, 1, 1},
                {2, 2, 2, 2},
                {3, 3, 3, 3},
                {2, 2, 2, 2}};

        int[][] b = {{1, 1, 1, 1},
                {2, 2, 2, 2},
                {3, 3, 3, 3},
                {2, 2, 2, 2}};

        printMat(a, "A");
        printMat(b, "B");

        int[][] result = new StrassenMatrixMultiplication().multiply(a, b);

        printMat(result, "Result");
    }

    private void printMat(int[][] a, String message) {
        System.out.println(message);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
