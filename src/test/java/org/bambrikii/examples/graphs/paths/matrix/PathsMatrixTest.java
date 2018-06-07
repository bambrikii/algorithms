package org.bambrikii.examples.graphs.paths.matrix;

import org.junit.Test;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 03/06/18 09:21.
 */
public class PathsMatrixTest {
    @Test
    public void testMatrixPath() {
        int[][] graph = new int[][]{
                {1, 0, 0, 1, 0},
                {1, 1, 1, 1, 0},
                {0, 2, 0, 1, 0},
                {4, 3, 0, 1, 1},
                {5, 0, 0, 0, 1},
                {1, 1, 0, 1, 1},
                {0, 1, 1, 0, 1},
                {0, 0, 1, 1, 1}
        };

        int maxX = graph.length;
        int maxY = graph[0].length;
        PathMatrixFinder finder = new PathMatrixFinder(graph, maxX, maxY);
        List<List<int[]>> paths = finder.findPaths();
        finder.printPaths(paths);
    }
}
