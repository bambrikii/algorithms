package org.bambrikii.examples.graphs.maxflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FordFulkersonAlgo {
    private final Map<Integer, List<Edge>> edges = new HashMap<>();

    public FordFulkersonAlgo edge(int from, int to, int val) {
        if (!edges.containsKey(from)) {
            edges.put(from, new ArrayList<>());
        }
        edges.get(from).add(new Edge(from, to, val, 0));
        return this;
    }

    public Integer find(int from, int to) {
        Integer maxFlow = findPath(from, to, Integer.MAX_VALUE);

        System.out.println("Path:");
        LinkedList<Edge> queue = new LinkedList<>();
        queue.addAll(edges.get(from));
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            System.out.print(" " + edge);
            Integer to2 = edge.getTo();
            if (to2 != null) {
                List<Edge> to2Edges = edges.get(to2);
                if (to2Edges != null && !to2Edges.isEmpty()) {
                    queue.addAll(to2Edges);
                }
            }
            if (edge.getFrom() == from) {
                System.out.print(" start: ");
            }
            if (edge.getTo() == to) {
                System.out.print(" end: " + edge.getFlow());
            }
            System.out.println();
        }

        System.out.println("Max flow by value = " + maxFlow);
        System.out.println("Max flow by from edges = " + edges.get(from).stream().mapToInt(edge -> edge.getFlow()).sum());

        return maxFlow;
    }

    private Integer findPath(Integer from, Integer to, Integer parentFlow) {
        if (from == to) {
            return parentFlow;
        }
        List<Edge> fromEdges = edges.get(from);
        if (fromEdges == null || fromEdges.isEmpty()) {
            return 0;
        }
        Integer residualFlow = fromEdges.stream().mapToInt(edge -> edge.getCapacity()).sum();
        if (parentFlow < residualFlow) {
            residualFlow = parentFlow;
        }
        Integer edgeFlow = 0;
        for (Edge fromEdge : fromEdges) {
            Integer capacity = fromEdge.getCapacity();
            if (capacity < residualFlow) {
                int childFlow = findPath(fromEdge.getTo(), to, capacity);
                fromEdge.setFlow(fromEdge.getFlow() + childFlow);
                edgeFlow += childFlow;
                residualFlow -= childFlow;
            } else if (capacity >= residualFlow) {
                int childFlow = findPath(fromEdge.getTo(), to, residualFlow);
                fromEdge.setFlow(childFlow);
                edgeFlow += childFlow;
                residualFlow -= childFlow;
            } else {
                fromEdge.setFlow(0);
            }
        }
        return edgeFlow;
    }
}
