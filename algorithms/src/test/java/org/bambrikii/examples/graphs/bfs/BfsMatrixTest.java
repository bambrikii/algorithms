package org.bambrikii.examples.graphs.bfs;

import org.junit.jupiter.api.Test;

public class BfsMatrixTest {
    @Test
    public void shouldFind() {
        int[][] arr = new int[][]{
                {1, 1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1}
        };
        BfsMatrix.BfsMatrixResult result = new BfsMatrix(arr)
                .find(
                        new int[]{0, 0},
                        new int[]{2, 5}
                );

        System.out.println(result.weight);

        for (int[] rows : result.weights) {
            for (int row : rows) {
                System.out.print(" " + row);
            }
            System.out.println();
        }

        for (int[][] prev : result.prevs) {
            for (int[] prev1 : prev) {
                int prevX = prev1[0];
                int prevY = prev1[1];
                System.out.printf(" %sx%s=(%s)", prevX, prevY, result.weights[prevX][prevY]);
            }
            System.out.println();
        }
    }
}
