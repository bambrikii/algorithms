package org.bambrikii.examples.graphs.mstree;

import org.bambrikii.examples.graphs.mstree.KruskalAlgoMatrix.Edge;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    static void printOutput(List<Edge> edges) {
        int w = 0;
        for (Edge edge : edges) {
            System.out.println(edge);
            w += edge.getW();
        }

        System.out.println("Weight: " + w);
    }

    @Test
    public void shouldFind() {
        KruskalAlgoMatrix algo = new KruskalAlgoMatrix();

        KruskalAlgoMatrix.KruskalAlgoMatrixResult result = algo.find(buildInput());

        printOutput(result.getEdges());

        for (Integer processed : result.getProcessed()) {
            System.out.print(" -> " + processed);
        }
        System.out.println();
    }
}
