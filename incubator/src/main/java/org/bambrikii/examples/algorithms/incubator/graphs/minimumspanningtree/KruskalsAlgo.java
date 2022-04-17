package org.bambrikii.examples.algorithms.incubator.graphs.minimumspanningtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KruskalsAlgo extends AbstractMinimumSpanningTreeAlgo {
    public static void main(String[] args) {
        KruskalsAlgo algo = new KruskalsAlgo()
                .edge(2, 3, 1)
                .edge(2, 4, 3)
                .edge(3, 4, 5)
                .edge(3, 5, 2)
                .edge(7, 5, 11);
        List<Edge> result = algo.build();
        algo.print(result);
    }

    /**
     * Edges are sorted by weight,
     * and are added to result in order
     * while they do not form a connected component.
     * <p>
     * https://www.youtube.com/watch?v=71UQH7Pr9kU
     *
     * @return
     */
    @Override
    public List<Edge> build() {
        List<Edge> sorted = new ArrayList<>();
        edges.values().forEach(e -> sorted.addAll(e));
        Collections.sort(sorted, Comparator.comparingInt(Edge::weight));

        List<Edge> components = new ArrayList<>();
        Map<Integer, Integer> union = new HashMap<>();

        for (Edge edge : sorted) {
            if (find(edge.from(), union) == find(edge.to(), union)) {
                continue;
            }
            union(edge.from(), edge.to(), union);
            ensureComponent(components, edge);
        }
        return components;
    }

    private void ensureComponent(List<Edge> components, Edge edge) {
        if (!components.contains(new Edge(edge.to(), edge.from(), edge.weight()))) {
            components.add(edge);
        }
    }

    private int find(int from, Map<Integer, Integer> visited) {
        if (!visited.containsKey(from)) {
            visited.put(from, from);
        }
        Integer parent = visited.get(visited.get(from));
        if (parent != from) {
            parent = find(parent, visited);
            visited.put(from, parent);
            from = parent;
        }
        return from;
    }

    private void union(int from, int to, Map<Integer, Integer> visited) {
        from = find(from, visited);
        to = find(to, visited);
        if (from < to) {
            visited.put(from, to);
        } else if (to < from) {
            visited.put(to, from);
        }
    }
}
