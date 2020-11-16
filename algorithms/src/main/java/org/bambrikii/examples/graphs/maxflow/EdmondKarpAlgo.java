package org.bambrikii.examples.graphs.maxflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://www.youtube.com/watch?v=95_E2EBPEHo&ab_channel=MichaelChristensen
 */
public class EdmondKarpAlgo {
    private final Map<Integer, List<EdmondKarpEdge>> edges = new HashMap<>();

    public EdmondKarpAlgo edge(int from, int to, int val) {
        if (!edges.containsKey(from)) {
            edges.put(from, new ArrayList<>());
        }
        edges.get(from).add(new EdmondKarpEdge(from, to, val, 0));
        return this;
    }

    public void find(Integer from, Integer to) {
        EdmondKarpEdge path = findPath(from, to);
        while (path != null) {
            findMin(path);
            path = findPath(from, to);
        }
    }

    private void findMin(EdmondKarpEdge path) {
        EdmondKarpEdge edge = path;
        Integer min = null;
        while (edge != null) {
            int cap = edge.getCapacity() - edge.getFlow();
            if (min == null || min > cap) {
                min = cap;
            }
            edge = edge.getPrev();
        }
        edge = path;
        if (min == null) {
            return;
        }
        while (edge != null) {
            edge.setFlow(edge.getFlow() + min);
            edge = edge.getPrev();
        }
    }

    private EdmondKarpEdge findPath(Integer from, Integer to) {
        LinkedList<EdmondKarpEdge> queue = new LinkedList<>(edges.get(from)
                .stream()
                .filter(edge2 -> edge2.getCapacity() - edge2.getFlow() > 0)
                .collect(Collectors.toList())
        );
        HashMap<Integer, Integer> weights = new HashMap<>();
        EdmondKarpEdge toEdge = null;
        while (!queue.isEmpty()) {
            EdmondKarpEdge edge = queue.poll();

            if (weight(weights, edge) == null) {
                weight(weights, edge, 0);
            }

            int cap = edge.getCapacity() - edge.getFlow();
            if (cap > 0) {
                List<EdmondKarpEdge> edges2 = edges.get(edge.getTo());
                if (edges2 == null) {
                    continue;
                }
                for (EdmondKarpEdge edge2 : edges2) {
                    if (edge2.getFlow() >= edge2.getCapacity()) {
                        continue;
                    }
                    int candidateN = weight(weights, edge) + 1;
                    Integer weight = weight(weights, edge2);
                    if (weight != null && weight <= candidateN) {
                        continue;
                    }
                    weight(weights, edge2, candidateN);
                    edge2.setPrev(edge);
                    queue.add(edge2);
                    if (edge2.getTo() == to) {
                        toEdge = edge2;
                    }
                }
            }
        }
        return toEdge;
    }

    private void weight(HashMap<Integer, Integer> weights, EdmondKarpEdge edge2, int candidateN) {
        weights.put(edge2.getTo(), candidateN);
    }

    private Integer weight(HashMap<Integer, Integer> weights, EdmondKarpEdge edge) {
        return weights.containsKey(edge.getTo()) ? weights.get(edge.getTo()) : null;
    }


    public void print(Integer start, Integer end) {
        LinkedList<EdmondKarpEdge> queue = new LinkedList<>();
        List<EdmondKarpEdge> edges = this.edges.get(start);
        if (edges == null || edges.isEmpty()) {
            return;
        }
        queue.addAll(edges);
        while (!queue.isEmpty()) {
            EdmondKarpEdge edge = queue.poll();
            System.out.println(edge);
            edges = this.edges.get(edge.getTo());
            if (edges != null && !edges.isEmpty()) {
                queue.addAll(edges);
            }
        }

        System.out.println("Max flow = " + this.edges.get(start).stream().mapToInt(edge -> edge.getFlow()).sum());
    }
}
