package org.bambrikii.examples.graphs.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * https://www.youtube.com/watch?v=8MpoO2zA2l4&ab_channel=WilliamFiset
 */
public class EulerPathAlgo {
    private final Map<Integer, List<EulerPathEdge>> edgesFrom = new HashMap<>();
    private final List<EulerPathEdge> edges = new ArrayList<>();

    public EulerPathAlgo undirectedEdge(int from, int to) {
        EulerPathEdge edge = EulerPathEdge.undirected(from, to);
        edges.add(edge);
        ensureEdge(from, edge);
        ensureEdge(to, edge);
        return this;
    }

    public EulerPathAlgo directedEdge(int from, int to) {
        EulerPathEdge edge = EulerPathEdge.directed(from, to);
        edges.add(edge);
        ensureEdge(from, edge);
        return this;
    }

    private void ensureEdge(int from, EulerPathEdge edge) {
        if (!edgesFrom.containsKey(from)) {
            edgesFrom.put(from, new ArrayList<>());
        }
        edgesFrom.get(from).add(edge);
    }

    private Map<Integer, EulerPathCounts> countEdges() {
        Map<Integer, EulerPathCounts> counts = new HashMap<>();
        for (EulerPathEdge edge : edges) {
            ensureCounter(counts, edge.getFrom()).from();
            ensureCounter(counts, edge.getTo()).to();
        }
        return counts;
    }

    private EulerPathCounts ensureCounter(Map<Integer, EulerPathCounts> counts, Integer vertex) {
        if (!counts.containsKey(vertex)) {
            counts.put(vertex, new EulerPathCounts());
        }
        return counts.get(vertex);
    }

    private List<Entry<Integer, EulerPathCounts>> validateEdgesCount(Map<Integer, EulerPathCounts> edgesCount) {
        Predicate<? super Entry<Integer, EulerPathCounts>> filter = new Predicate<Entry<Integer, EulerPathCounts>>() {
            @Override
            public boolean test(Entry<Integer, EulerPathCounts> entry) {
                return Math.abs(entry.getValue().getFrom() - entry.getValue().getTo()) > 1;
            }
        };
        List<Entry<Integer, EulerPathCounts>> oddEdges = edgesCount
                .entrySet()
                .stream()
                .filter(filter)
                .collect(Collectors.toList());
        if (oddEdges.size() > 2) {
            throw new UnsupportedOperationException("The following vertexes have odd number of edges: " + oddEdges);
        }
        return oddEdges;
    }

    public List<List<Integer>> findPaths() {
        List<List<Integer>> paths = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        Map<Integer, EulerPathCounts> edgesCount = countEdges();
        List<Entry<Integer, EulerPathCounts>> oddEdges = validateEdgesCount(edgesCount);

        int oddEdgesSize = oddEdges.size();
        if (oddEdgesSize == 2) {
            Integer vertexFrom1 = oddEdges.get(0).getKey();
            Integer vertexFrom2 = oddEdges.get(1).getKey();
            findPath(vertexFrom1, path, paths, 0);
            findPath(vertexFrom2, path, paths, 0);
        } else if (oddEdgesSize == 1) {
            Integer vertexFrom1 = oddEdges.get(0).getKey();
            findPath(vertexFrom1, path, paths, 0);
        } else {
            for (Integer vertexFrom : edgesFrom.keySet()) {
                findPath(vertexFrom, path, paths, 0);
            }
        }
        return paths;
    }

    private void findPath(Integer vertexFrom, LinkedList<Integer> path, List<List<Integer>> paths, int depth) {
        path.addLast(vertexFrom);
        List<EulerPathEdge> edgesFrom = this.edgesFrom.get(vertexFrom);
        if (edgesFrom != null) {
            for (EulerPathEdge edgeFrom : edgesFrom) {
                if (edgeFrom.isVisited()) {
                    continue;
                }
                edgeFrom.setVisited(true);
                Integer vertexTo = edgeFrom.other(vertexFrom);
                findPath(vertexTo, path, paths, depth + 1);
                edgeFrom.setVisited(false);
            }
        }
        if (depth == edges.size()) { // Euler's path found
            paths.add(new ArrayList<>(path));
        }
        path.removeLast();
    }

    public void printPaths(List<List<Integer>> paths) {
        for (List<Integer> path : paths) {
            System.out.println("Path: " + path);
        }
    }
}

