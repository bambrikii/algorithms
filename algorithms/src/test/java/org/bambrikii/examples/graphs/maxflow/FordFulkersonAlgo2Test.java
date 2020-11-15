package org.bambrikii.examples.graphs.maxflow;

import org.junit.jupiter.api.Test;

public class FordFulkersonAlgo2Test {
    @Test
    public void shouldFind() {
        FordFulkersonAlgo2 algo = new FordFulkersonAlgo2();

        algo
                .edge(1, 2, 3)
                .edge(2, 3, 2)
                .edge(3, 4, 1)
                .edge(4, 9, 1)
                .edge(2, 10, 3)
                .edge(10, 9, 3)
                .edge(1, 5, 3)
                .edge(5, 9, 2)
                .edge(1, 6, 4)
                .edge(6, 9, 3)
        ;

        System.out.println("Max flow = " + algo.calculateMaxFlow(1, 9));
        algo.print(1, 9);
    }
}
