package org.bambrikii.examples.graphs.bfs;

import org.junit.jupiter.api.Test;

public class BfsTest {
    @Test
    public void shouldFindShortestPath() {
        Bfs<Integer> bfs = new Bfs<>();
        bfs
                .edge(1, 2, 2)
                .edge(1, 3, 3)
                .edge(1, 4, 4)
                .edge(2, 3, 3)
                .edge(2, 4, 1)
                .edge(3, 4, 4)

        ;

        BfsDistance<Integer> result = bfs.find(1, 4);
        System.out.println(" " + result);
    }
}
