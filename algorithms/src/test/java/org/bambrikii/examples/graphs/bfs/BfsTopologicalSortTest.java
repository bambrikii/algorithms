package org.bambrikii.examples.graphs.bfs;

import org.bambrikii.examples.graphs.dfs.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bambrikii.examples.graphs.dfs.DfsTopologicalSortTest.createGraph;

public class BfsTopologicalSortTest {
    @Test
    public void shouldSort() {
        BfsTopologicalSort algo = new BfsTopologicalSort();
        List<Node> result = algo.sort(createGraph());

        BfsTopologicalSort.print(result);

        assertThat(result.indexOf(new Node(1))).isLessThan(result.indexOf(new Node(2)));
        assertThat(result.indexOf(new Node(2))).isLessThan(result.indexOf(new Node(5)));
        assertThat(result.indexOf(new Node(3))).isLessThan(result.indexOf(new Node(4)));
        assertThat(result.indexOf(new Node(4))).isLessThan(result.indexOf(new Node(5)));
    }
}
