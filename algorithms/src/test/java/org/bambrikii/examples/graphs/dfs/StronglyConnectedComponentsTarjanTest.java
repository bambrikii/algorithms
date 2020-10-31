package org.bambrikii.examples.graphs.dfs;

import org.junit.jupiter.api.Test;

import java.util.List;

public class StronglyConnectedComponentsTarjanTest {
    @Test
    public void shouldFind() {
        StronglyConnectedComponentsTarjan algo = new StronglyConnectedComponentsTarjan();

        algo
                .edge(0, 1)
                .edge(1, 2)
                .edge(2, 3)
                .edge(2, 4)
                .edge(3, 0)
                .edge(4, 5)
                .edge(5, 6)
                .edge(6, 4)
                .edge(6, 7);

        List<List<StronglyConnectedComponentsTarjanNode>> result = algo.list();

        algo.print(result);
    }
}
