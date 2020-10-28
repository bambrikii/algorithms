package org.bambrikii.examples.graphs.bfs;

import org.bambrikii.examples.graphs.dfs.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.bambrikii.examples.graphs.dfs.DfsTopologicalSortTest.createGraph;

public class BfsDfsTopologicalSortTest {
    @Test
    public void shouldSort() {
        BfsTopologicalSort algo = new BfsTopologicalSort();
        List<Node> result = algo.sort(createGraph());

        BfsTopologicalSort.print(result);
    }
}
