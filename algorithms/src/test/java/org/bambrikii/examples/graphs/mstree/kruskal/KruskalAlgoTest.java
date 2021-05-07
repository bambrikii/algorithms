package org.bambrikii.examples.graphs.mstree.kruskal;

import org.bambrikii.examples.graphs.mstree.Edge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://www.programiz.com/dsa/kruskal-algorithm
 */
public class KruskalAlgoTest {
    private KruskalAlgo algo;

    @BeforeEach
    public void before() {
        algo = new KruskalAlgo();
    }

    @Test
    public void shouldFind1() {
        List<Edge> result = algo
                .edge(1, 1, 2)
                .edge(2, 2, 3)
                .edge(3, 1, 3)
                .msp();

        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isEqualToComparingFieldByField(new Edge(1, 1, 2));
        assertThat(result.get(1)).isEqualToComparingFieldByField(new Edge(2, 2, 3));
    }

    @Test
    public void shouldFind2() {
        List<Edge> result = algo
                .edge(10, 1, 2)
                .edge(9, 2, 3)
                .edge(8, 3, 4)
                .edge(7, 4, 5)
                .edge(6, 5, 6)
                .edge(5, 6, 7)
                .edge(5, 6, 8)
                .edge(5, 6, 9)
                .edge(4, 7, 8)
                .edge(3, 8, 9)
                .edge(3, 8, 10)
                .edge(2, 9, 10)
                .edge(1, 10, 11)
                .msp();

        result.forEach(System.out::println);

        assertThat(result).hasSize(10);
    }
}
