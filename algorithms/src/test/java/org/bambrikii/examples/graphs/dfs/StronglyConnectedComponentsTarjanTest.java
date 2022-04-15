package org.bambrikii.examples.graphs.dfs;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

public class StronglyConnectedComponentsTarjanTest {
    @Test
    public void shouldFind() {
        StronglyConnectedComponentsTarjan algo = new StronglyConnectedComponentsTarjan();

        algo
                .edge(0, 1)
                .edge(1, 2)
                .edge(2, 3)
                .edge(3, 0)

                .edge(2, 4)

                .edge(4, 5)
                .edge(5, 6)
                .edge(6, 4)

                .edge(6, 7);

        Collection<List<StronglyConnectedComponentsTarjanNode>> result = algo
                .build()
                .print()
                .collect();

        algo.print(result);
    }

    @Test
    public void shouldFindNestedLoopsInComponents() {
        StronglyConnectedComponentsTarjan algo = new StronglyConnectedComponentsTarjan();

        algo
                .edge(2, 8)
                .edge(8, 9)
                .edge(9, 2)

                .edge(0, 1)
                .edge(1, 2)
                .edge(2, 3)
                .edge(3, 0)

                .edge(2, 4)

                .edge(4, 5)
                .edge(5, 6)
                .edge(6, 4)

                .edge(6, 7);

        Collection<List<StronglyConnectedComponentsTarjanNode>> result = algo
                .build()
                .print()
                .collect();

        algo.print(result);
    }
}
