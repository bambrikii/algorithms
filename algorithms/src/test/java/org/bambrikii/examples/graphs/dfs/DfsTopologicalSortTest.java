package org.bambrikii.examples.graphs.dfs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * https://www.coursera.org/lecture/algorithms-graphs-data-structures/topological-sort-yeKm7
 */
public class DfsTopologicalSortTest {
    private DfsTopologicalSort algo;

    @BeforeEach
    public void before() {
        algo = new DfsTopologicalSort();
    }

    public static Node[] createGraph() {
        Node node5 = new Node(5);
        Node node4 = new Node(4, node5);
        Node node11 = new Node(11, node5);
        Node node3 = new Node(3, node4, node11);
        Node node2 = new Node(2, node5);

        Node root = new Node(1, node2, node3, node4);
        return new Node[]{root};
    }

    @Test
    public void shouldSort() {
        // given
        Node[] graph = createGraph();

        // when
        List<Node> result = algo.sort(graph);

        // then
        algo.print(result);

        assertThat(result.indexOf(new Node(1))).isLessThan(result.indexOf(new Node(2)));
        assertThat(result.indexOf(new Node(2))).isLessThan(result.indexOf(new Node(5)));
        assertThat(result.indexOf(new Node(3))).isLessThan(result.indexOf(new Node(4)));
        assertThat(result.indexOf(new Node(4))).isLessThan(result.indexOf(new Node(5)));
        assertThat(result.indexOf(new Node(3))).isLessThan(result.indexOf(new Node(11)));
        assertThat(result.indexOf(new Node(11))).isLessThan(result.indexOf(new Node(5)));
    }
}
