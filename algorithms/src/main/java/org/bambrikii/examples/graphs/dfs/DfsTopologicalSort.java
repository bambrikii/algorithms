package org.bambrikii.examples.graphs.dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.TRUE;

public class DfsTopologicalSort {
    public List<Node> sort(Node... roots) {
        LinkedList<Node> result = new LinkedList<>();
        Map<Node, Boolean> visited = new HashMap<>();

        for (Node root : roots) {
            dfs(root, result, visited);
        }

        return result;
    }

    private void dfs(Node node, LinkedList<Node> result, Map<Node, Boolean> visited) {
        for (Node child : node.getChildren()) {
            if (visited.containsKey(child)) {
                continue;
            }

            visited.put(child, TRUE);

            dfs(child, result, visited);
        }
        result.addFirst(node);
    }

    public void print(List<Node> result) {
        int n = result.size();
        for (int i = 0; i < n; i++) {
            Node node = result.get(i);
            System.out.print(" " + node.getVal());
        }
    }
}
