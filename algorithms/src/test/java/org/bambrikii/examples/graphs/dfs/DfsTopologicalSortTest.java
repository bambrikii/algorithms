package org.bambrikii.examples.graphs.dfs;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * https://www.coursera.org/lecture/algorithms-graphs-data-structures/topological-sort-yeKm7
 */
public class DfsTopologicalSortTest {
    public static Node[] createGraph() {
        Node node5 = new Node(5);
        Node node3 = new Node(3, node5);
        Node root = new Node(1,
                new Node(2, node5),
                node3,
                new Node(4, node3)
        );
        return new Node[]{root};
    }

    @Test
    public void shouldSort() {
        DfsTopologicalSort algo = new DfsTopologicalSort();

        List<Node> result = algo.sort(createGraph());

        algo.print(result);
    }
}
