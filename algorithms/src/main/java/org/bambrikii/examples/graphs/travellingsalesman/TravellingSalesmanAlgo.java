package org.bambrikii.examples.graphs.travellingsalesman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Travelling Salesman Algorithm - Naive Approach
 */
public class TravellingSalesmanAlgo {
    private final Map<Integer, TravellingSalesmanVertex> vertexes = new HashMap<>();
    private final Map<Integer, Map<Integer, Integer>> calculatedWeights = new HashMap<>();
    private final List<TravellingSalesmanResult> results = new ArrayList<>();

    public TravellingSalesmanAlgo undirectedEdge(int from, int to, int weight) {
        TravellingSalesmanEdge edge = TravellingSalesmanEdge.undirected(from, to, weight);
        ensureVertex(from, edge);
        ensureVertex(to, edge);
        return this;
    }

    public TravellingSalesmanAlgo directedEdge(int from, int to, int weight) {
        TravellingSalesmanEdge edge = TravellingSalesmanEdge.directed(from, to, weight);
        ensureVertex(from, edge);
        return this;
    }

    private void ensureVertex(int from, TravellingSalesmanEdge edge) {
        if (!vertexes.containsKey(from)) {
            vertexes.put(from, new TravellingSalesmanVertex());
        }
        vertexes.get(from).getEdges().add(edge);
    }

    public void find() {
        int depth = 1;
        LinkedHashSet<Integer> visited = new LinkedHashSet<>();
        for (Entry<Integer, TravellingSalesmanVertex> entry : vertexes.entrySet()) {
            Integer vertexId = entry.getKey();
            visited.add(vertexId);

            TravellingSalesmanVertex vertex = entry.getValue();
            for (TravellingSalesmanEdge edge : vertex.getEdges()) {
                if (visited.contains(edge.getTo())) {
                    continue;
                }
                visited.add(edge.getTo());
                findWeight(vertexId, edge.getTo(), edge.getWeight(), visited, depth + 1);
                visited.remove(edge.getTo());
            }

            visited.remove(vertexId);
        }

    }

    private void findWeight(Integer from, Integer to, Integer weight, LinkedHashSet<Integer> visited, int depth) {
        TravellingSalesmanVertex vertex = vertexes.get(to);
        for (TravellingSalesmanEdge edge : vertex.getEdges()) {
            if (visited.contains(edge.getTo())) {
                if (depth == vertexes.size() && from == edge.getTo()) {
                    results.add(new TravellingSalesmanResult(weight + edge.getWeight(), new LinkedHashSet<>(visited)));
                }
                continue;
            }
            visited.add(edge.getTo());
            findWeight(from, edge.getTo(), weight + edge.getWeight(), visited, depth + 1);
            visited.remove(edge.getTo());
        }
    }

    public List<TravellingSalesmanResult> results() {
        return results;
    }

    public void print() {
        Integer leastWeight = null;
        List<TravellingSalesmanResult> leastPaths = new ArrayList<>();

        System.out.println("All Paths:");
        for (TravellingSalesmanResult result : results) {
            System.out.println(" " + result);
            if (leastWeight == null || leastWeight > result.getWeight()) {
                leastWeight = result.getWeight();
                leastPaths.clear();
                leastPaths.add(result);
            } else if (leastWeight != null && leastWeight == result.getWeight()) {
                leastPaths.add(result);
            }
        }
        System.out.println("Least Paths (weight: " + leastWeight + "):");
        for (TravellingSalesmanResult leastPath : leastPaths) {
            System.out.println(" " + leastPath);
        }
    }
}
