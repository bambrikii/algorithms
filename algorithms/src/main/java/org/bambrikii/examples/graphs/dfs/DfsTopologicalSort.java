package org.bambrikii.examples.graphs.dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class DfsTopologicalSort {
    public List<Node> sort(Node... roots) {
        LinkedList<Node> result = new LinkedList<>();
        Map<Node, Boolean> visited = new HashMap<>();
        Map<Node, Integer> in = new HashMap<>();

        for (Node root : roots) {
            prepareIns(root, in);
        }
        for (Node root : roots) {
            dfs(root, result, visited, in);
        }

        return result;
    }

    private void dfs(Node node, LinkedList<Node> result, Map<Node, Boolean> visited, Map<Node, Integer> in) {
        for (Node child : node.getChildren()) {
            if (visited.containsKey(child)) {
                continue;
            }
            if (!in.containsKey(child)) {
                continue;
            }
            if (in.get(child) == 0) {
                continue;
            }

            visited.put(child, TRUE);
            in.put(child, in.get(child) - 1);

            dfs(child, result, visited, in);

            visited.put(child, FALSE);
        }
        result.addFirst(node);
    }

    private void prepareIns(Node node, Map<Node, Integer> in) {
        if (node == null) {
            return;
        }
        if (!in.containsKey(node)) {
            in.put(node, 0);
        }
        for (Node child : node.getChildren()) {
            if (!in.containsKey(child)) {
                in.put(child, 0);
            }
            in.put(child, in.get(child) + 1);
            prepareIns(child, in);
        }
    }

    public void print(List<Node> result) {
        int n = result.size();
        for (int i = 0; i < n; i++) {
            Node node = result.get(i);
            System.out.print(" " + node.getVal());
        }
    }
}
