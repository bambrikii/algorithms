package org.bambrikii.examples.algorithms.incubator.graphs.bipartite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class BipartiteGraphAlgo {
    private boolean result;

    public static void main(String[] args) {
        main1();
        main2();
    }

    private static void main1() {
        new BipartiteGraphAlgo()
                .edge(1, 2)
                .edge(1, 3)
                .edge(3, 4)
                .find()
                .print();
    }

    private static void main2() {
        new BipartiteGraphAlgo()
                .edge(1, 2)
                .edge(1, 3)
                .edge(3, 4)
                .edge(3, 2)
                .find()
                .print();
    }

    private final Map<Integer, Set<Integer>> edges = new HashMap<>();

    private BipartiteGraphAlgo edge(int from, int to) {
        ensureEdge(from, to);
        ensureEdge(to, from);
        return this;
    }

    private void ensureEdge(int from, int to) {
        if (!edges.containsKey(from)) {
            edges.put(from, new HashSet<>());
        }
        edges.get(from).add(to);
    }

    private BipartiteGraphAlgo find() {
        result = true;
        Map<Integer, Boolean> visited = new HashMap<>();
        LinkedList<Integer> queue = new LinkedList<>();
        Integer next = findNext(queue, visited);
        if (next == null) {
            return this;
        }
        queue.add(next);
        visited.put(next, true);
        while (!queue.isEmpty()) {
            next = queue.poll();
            Set<Integer> edges2 = edges.get(next);
            Boolean on = visited.get(next);
            if (edges2 == null || edges2.isEmpty()) {
                continue;
            }
            for (Integer edge2 : edges2) {
                if (visited.containsKey(edge2)) {
                    if (visited.get(edge2) == on) {
                        result = false;
                        break;
                    }
                } else {
                    visited.put(edge2, !on);
                    queue.add(edge2);
                }
            }
        }
        return this;
    }

    private Integer findNext(LinkedList<Integer> queue, Map<Integer, Boolean> visited) {
        for (Integer edge : edges.keySet()) {
            if (!visited.containsKey(edge)) {
                return edge;
            }
        }
        return null;
    }

    private BipartiteGraphAlgo print() {
        System.out.println(result);
        return this;
    }
}
