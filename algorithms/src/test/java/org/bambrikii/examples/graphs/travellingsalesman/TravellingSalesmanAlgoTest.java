package org.bambrikii.examples.graphs.travellingsalesman;

import org.junit.jupiter.api.Test;

public class TravellingSalesmanAlgoTest {
    @Test
    public void shouldTest1() {
        TravellingSalesmanAlgo algo = new TravellingSalesmanAlgo();

        algo
                .directedEdge(1, 2, 1)
                .directedEdge(2, 3, 2)
                .directedEdge(3, 4, 3)
                .directedEdge(4, 1, 4)

                .directedEdge(2, 4, 1)
                .directedEdge(4, 3, 1)
                .directedEdge(3, 5, 1)
                .directedEdge(4, 5, 1)
                .directedEdge(5, 6, 5)
                .directedEdge(6, 1, 6)
        ;

        algo.find();

        algo.print();
    }
}
