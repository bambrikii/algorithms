package org.bambrikii.examples.mstree;

import org.bambrikii.examples.mstree.KruskalAlgoMatrix.Edge;
import org.junit.jupiter.api.Test;

public class KruskalAlgoMatrixTest {
    static int[][] buildInput() {
        return new int[][]{
                {0, 2, 3, 4, 5},
                {2, 0, 1, 1, 4},
                {3, 1, 0, 3, 3},
                {4, 1, 3, 0, 2},
                {5, 4, 3, 2, 0},
        };
    }

    @Test
    public void shouldFind() {
        KruskalAlgoMatrix algo = new KruskalAlgoMatrix();
        KruskalAlgoMatrix.KruskalAlgoMatrixResult result = algo.find(buildInput());
        int w = 0;
        for (Edge edge : result.getEdges()) {
            System.out.println(edge);
            w += edge.getW();
        }

        System.out.println("Weight: " + w);

        for (Integer processed : result.getProcessed()) {
            System.out.print(" -> " + processed);
        }
        System.out.println();
    }
}
