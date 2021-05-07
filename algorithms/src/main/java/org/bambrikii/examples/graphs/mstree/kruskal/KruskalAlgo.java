package org.bambrikii.examples.graphs.mstree.kruskal;

import org.bambrikii.examples.graphs.mstree.Edge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://www.hackerearth.com/practice/algorithms/graphs/minimum-spanning-tree/tutorial/
 */
public class KruskalAlgo {
    private Map<Integer, List<Edge>> edgesMap = new HashMap<>();
    private Map<Integer, Integer> parents = new HashMap<>();

    public KruskalAlgo edge(int weight, int from, int to) {
        Edge edge = new Edge(weight, from, to);
        if (!edgesMap.containsKey(from)) {
            edgesMap.put(from, new ArrayList<>());
        }
        edgesMap.get(from).add(edge);
        parents.putIfAbsent(from, from);
        parents.putIfAbsent(to, to);
        return this;
    }

    private Integer find(int child) {
        Integer parent = parents.get(child);
        if (parent == child) {
            return child;
        }
        if (parent != find(parent)) {
            parents.put(child, find(parent));
        }
        return parents.get(child);
    }

    private void union(int from, int to) {
        parents.put(from, to);
    }

    public List<Edge> msp() {
        List<Edge> msp = new ArrayList<>();

        ArrayList<Edge> edges = new ArrayList<>(this.edgesMap.values()
                .stream()
                .flatMap(e -> e.stream())
                .sorted(Comparator.comparingInt(o -> o.getW()))
                .collect(Collectors.toList())
        );

        for (Edge edge : edges) {
            int from = edge.getFrom();
            int to = edge.getTo();

            Integer fromParent = find(from);
            Integer toParent = find(to);

            if (fromParent != toParent) {
                msp.add(edge);
                union(from, to);
            }
        }
        return msp;
    }

}
