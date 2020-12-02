package org.bambrikii.examples.graphs.alledges;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EulerPathAlgoTest {
    @Test
    public void shouldFindPaths() {
        EulerPathAlgo algo = new EulerPathAlgo();

        algo
                .directedEdge(1, 2)
                .directedEdge(2, 2)
                .directedEdge(2, 4)
                .directedEdge(2, 4)
                .directedEdge(4, 6)
                .directedEdge(4, 3)
                .directedEdge(6, 3)
                .directedEdge(3, 1)
                .directedEdge(3, 2)
                .directedEdge(3, 5)
                .directedEdge(5, 6);

        List<List<Integer>> paths = algo.findPaths();

        algo.printPaths(paths);

        assertThat(paths).hasSize(32);
        assertThat(paths.get(0)).isEqualTo(Arrays.asList(3, 1, 2, 2, 4, 6, 3, 2, 4, 3, 5, 6));
    }

    @Test
    public void shouldFindPathsDirectedGraph() {
        EulerPathAlgo algo = new EulerPathAlgo();

        algo
                .directedEdge(0, 1)
                .directedEdge(1, 2)
                .directedEdge(1, 3)
                .directedEdge(2, 1)
                .directedEdge(3, 4);

        List<List<Integer>> paths = algo.findPaths();

        algo.printPaths(paths);

        assertThat(paths).isEqualTo(Arrays.asList(Arrays.asList(0, 1, 2, 1, 3, 4)));
    }

    @Test
    public void shouldFindCycle() {
        EulerPathAlgo algo = new EulerPathAlgo();

        algo
                .directedEdge(1, 2)
                .directedEdge(2, 3)
                .directedEdge(3, 1);

        List<List<Integer>> paths = algo.findPaths();

        algo.printPaths(paths);

        assertThat(paths).hasSize(3);
        assertThat(paths.get(0)).isEqualTo(Arrays.asList(1, 2, 3, 1));
    }
}
