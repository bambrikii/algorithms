package org.bambrikii.examples.graphs.bfs;

import org.bambrikii.examples.graphs.dfs.Node;

import java.util.*;

/**
 * https://www.hackerearth.com/practice/algorithms/graphs/topological-sort/tutorial/
 */
public class BfsTopologicalSort {
    public List<Node> sort(Node... roots) {
        List<Node> result = new ArrayList<>();

        Set<Node> visited = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<>();
        List<Node> rootNodes = Arrays.asList(roots);
        queue.addAll(rootNodes);
        result.addAll(rootNodes);
        visited.addAll(rootNodes);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node child : node.getChildren()) {
                if (visited.contains(child)) {
                    continue;
                }
                result.add(child);
                queue.add(child);
                visited.add(child);
            }
        }
        return result;
    }

    public static void print(List<Node> result) {
        int n = result.size();
        for (int i = 0; i < n; i++) {
            Node node = result.get(i);
            System.out.print(" " + node.getVal());
        }
    }
}
