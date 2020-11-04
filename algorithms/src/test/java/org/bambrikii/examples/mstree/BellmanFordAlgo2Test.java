package org.bambrikii.examples.mstree;

import org.junit.jupiter.api.Test;

import java.util.Map;

public class BellmanFordAlgo2Test {
    @Test
    public void shouldFind() {
        BellmanFordAlgo2 algo = new BellmanFordAlgo2();

        algo
                .edge(1, 2, 1)
                .edge(2, 3, 2)
                .edge(3, 4, 3)
                .edge(4, 6, 4)
                .edge(1, 5, 1)
                .edge(5, 6, 5)
        ;

        Map<Integer, BellmanFordEdge2> result = algo.find(1);

        algo.print(result);
    }

    @Test
    public void shouldFindWithNegative() {
        BellmanFordAlgo2 algo = new BellmanFordAlgo2();

        algo
                .edge(1, 2, 1)
                .edge(2, 3, 2)
                .edge(3, 4, 3)
                .edge(4, 6, -4)
                .edge(1, 5, 1)
                .edge(5, 6, 5)
        ;

        Map<Integer, BellmanFordEdge2> result = algo.find(1);

        algo.print(result);
    }
}
