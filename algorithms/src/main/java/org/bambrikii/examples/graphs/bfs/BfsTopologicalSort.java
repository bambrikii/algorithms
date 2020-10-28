package org.bambrikii.examples.graphs.bfs;

import org.bambrikii.examples.graphs.dfs.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://www.hackerearth.com/practice/algorithms/graphs/topological-sort/tutorial/
 */
public class BfsTopologicalSort {
    public List<Node> sort(Node... roots) {
        LinkedList<Node> q = new LinkedList<>();

        q.addAll(Arrays.asList(roots));

        Map<Node, Integer> in = new HashMap<>();

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (Node child : node.getChildren()) {
                if (!in.containsKey(child)) {
                    in.put(child, 0);
                }
                in.put(child, in.get(child) + 1);
                q.add(child);
            }
        }

        List<Node> result = new ArrayList<>();

        Map<Node, Boolean> visited = new HashMap<>();

        q.clear();
        q.addAll(Arrays.asList(roots));
        while (!q.isEmpty()) {
            Node node = q.poll();
            for (Node child : node.getChildren()) {
                if (!in.containsKey(child)) {
                    continue;
                }
                if (in.get(child) == 0) {
                    continue;
                }
                in.put(child, in.get(child) - 1);
                if (visited.containsKey(child) && visited.get(child)) {
                    continue;
                }
                q.add(child);
            }
            if (!visited.containsKey(node) || !visited.get(node)) {
                result.add(node);
            }
            visited.put(node, true);
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
