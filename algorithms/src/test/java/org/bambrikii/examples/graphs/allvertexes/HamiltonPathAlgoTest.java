package org.bambrikii.examples.graphs.allvertexes;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HamiltonPathAlgoTest {
    @Test
    public void shouldFindDirectedGraph() {
        HamiltonPathAlgo algo = new HamiltonPathAlgo();

        algo
                .directedEdge(1, 2)
                .directedEdge(2, 3)
                .directedEdge(3, 1);

        List<List<Integer>> result = algo.find();

        algo.print(result);

        assertThat(result).hasSize(3); // All are paths
        assertThat(result.get(0)).isEqualTo(Arrays.asList(1, 2, 3));
    }

    @Test
    public void shouldFindUndirectedGraph() {
        HamiltonPathAlgo algo = new HamiltonPathAlgo();

        algo
                .undirectedEdge(1, 2)
                .undirectedEdge(2, 3)
                .undirectedEdge(3, 1);

        List<List<Integer>> result = algo.find();

        algo.print(result);

        assertThat(result).hasSize(6); // All are cycles
        assertThat(result.get(0)).isEqualTo(Arrays.asList(1, 2, 3));
    }

    @Test
    public void shouldFindSample() {
        HamiltonPathAlgo algo = new HamiltonPathAlgo();

        algo
                .undirectedEdge(1, 2)
                .undirectedEdge(1, 3)
                .undirectedEdge(1, 7)

                .undirectedEdge(3, 2)
                .undirectedEdge(3, 4)
                .undirectedEdge(3, 5)
                .undirectedEdge(3, 6)

                .undirectedEdge(7, 2)
                .undirectedEdge(7, 6)

                .undirectedEdge(6, 4)
                .undirectedEdge(6, 5)

                .undirectedEdge(4, 5)
        ;

        List<List<Integer>> result = algo.find();

        algo.print(result);

        assertThat(result).hasSize(120);
        assertThat(result.get(0)).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6, 7)); // Cycle
        assertThat(result.get(2)).isEqualTo(Arrays.asList(1, 2, 7, 6, 3, 4, 5)); // Path
    }
}
