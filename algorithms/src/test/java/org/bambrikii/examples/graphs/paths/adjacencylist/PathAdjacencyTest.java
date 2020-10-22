package org.bambrikii.examples.graphs.paths.adjacencylist;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * Created by Alexander Arakelyan on 03/06/18 17:18.
 */
public class PathAdjacencyTest {
    @Test
    public void testAdjacencyPath() {
//        int[][][] graph = new int[][][]{
//                {{0, 1}, {2, 2}, {3, 2}},
//                {{2, 1}},
//                {{3, 1}},
//                {{2, 2}}
//        };

        int[][][] graph = new GraphBuilder()
                .edge(0, 0, 1).edge(0, 2, 2).edge(0, 3, 2)
                .edge(1, 2, 1)
                .edge(2, 3, 1)
                .edge(3, 2, 2)
                .build();

        PathAdjacencyFinder finder = new PathAdjacencyFinder(graph, 0, 0, 3, 2);
        LinkedList<LinkedList<int[]>> paths = finder.findPaths();
        finder.printPaths(paths);
    }
}

