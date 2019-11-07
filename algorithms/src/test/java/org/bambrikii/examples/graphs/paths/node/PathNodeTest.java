package org.bambrikii.examples.graphs.paths.node;

import org.junit.Test;

import java.util.List;

/**
 * Created by Alexander Arakelyan on 03/06/18 13:19.
 */
public class PathNodeTest {
    @Test
    public void testNodePath() {
        PathNode startingNode = new PathNode();
        PathNode endingNode = new PathNode();
        startingNode.addEdge(new PathNode(), 1).addEdge(new PathNode(), 2).addEdge(endingNode, 3);
        startingNode.addEdge(new PathNode(), 4).addEdge(new PathNode(), 5).addEdge(endingNode, 6);
        startingNode.addEdge(new PathNode(), 7).addEdge(new PathNode(), 8);
        startingNode.addEdge(new PathNode(), 9).addEdge(new PathNode(), 10).addEdge(new PathNode(), 11).addEdge(endingNode, 12);
        PathNodeGraphFinder finder = new PathNodeGraphFinder(startingNode, endingNode);
        List<List<PathEdge>> paths = finder.findPaths();
        finder.printPaths(paths);
    }

}
