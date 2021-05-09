package org.bambrikii.examples.graphs.bfs;

import org.bambrikii.examples.graphs.dfs.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://www.hackerearth.com/practice/algorithms/graphs/topological-sort/tutorial/
 */
public class BfsTopologicalSort {
    public List<Node> sort(Node... roots) {
        List<Node> rootNodes = Arrays.asList(roots);
        Map<Node, Integer> counts = count(rootNodes);
        List<Node> result = sort(rootNodes, counts);
        return result;
    }

    private boolean log = true;

    private void log(String message) {
        if (!log) {
            return;
        }
        System.out.println(message);
    }

    private Map<Node, Integer> count(List<Node> rootNodes) {
        Set<Node> visited = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<>();
        Map<Node, Integer> counts = new HashMap<>();
        for (Node rootNode : rootNodes) {
            counts.put(rootNode, 0);
            queue.add(rootNode);
            visited.add(rootNode);
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node child : node.getChildren()) {
                counts.put(child, counts.containsKey(child) ? counts.get(child) + 1 : 0);
                if (!visited.contains(child)) {
                    queue.add(child);
                    visited.add(child);
                }
            }
        }
        return counts;
    }

    private List<Node> sort(List<Node> rootNodes, Map<Node, Integer> counts) {
        Set<Node> visited = new LinkedHashSet<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.addAll(rootNodes);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int count = counts.get(node);
            if (!visited.contains(node)) {
                if (count == 0) {
                    log("getting count: " + node + " = " + count + ", adding to order");
                    visited.add(node);
                } else {
                    int newCount = count - 1;
                    log("setting count: " + node + " = " + newCount);
                    counts.put(node, newCount);
                }
            }
            for (Node child : node.getChildren()) {
                if (visited.contains(child)) {
                    log("skipping, visited: " + node);
                    continue;
                }
                queue.add(child);
            }
        }
        return new ArrayList<>(visited);
    }

    public static void print(List<Node> result) {
        int n = result.size();
        for (int i = 0; i < n; i++) {
            Node node = result.get(i);
            System.out.print(" " + node.getVal());
        }
    }
}
