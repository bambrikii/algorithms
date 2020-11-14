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
        edges.get(from).add(new Edge(from, to, val));
        return this;
    }

    public LinkedList<EdgeFlow> find(int from, int to) {
        LinkedList<EdgeFlow> path = findPath(from, to, new LinkedList<>());

        System.out.println("Path:");
        for (EdgeFlow edgeFlow : path) {
            System.out.print(" " + edgeFlow);
            if (edgeFlow.getEdge().getFrom() == from) {
                System.out.print(" > ");
            }
            if (edgeFlow.getEdge().getTo() == to) {
                System.out.print(" = " + edgeFlow.getFlow());
            }
            System.out.println();
        }

        int max = path
                .stream()
                .filter(edgeFlow -> edgeFlow.getEdge().getFrom() == from)
                .mapToInt(edgeFlow -> edgeFlow.getFlow())
                .sum();
        System.out.println("Max flow = " + max);

        return path;
    }

    private LinkedList<EdgeFlow> findPath(Integer from, Integer to, LinkedList<EdgeFlow> path) {
        if (from == null) {
            return path;
        }
        if (to == null) {
            return path;
        }
        List<Edge> fromEdges = edges.get(from);
        if (fromEdges == null || fromEdges.isEmpty()) {
            return path;
        }
        Integer flow = path.size() == 0 ? null : path.getLast().getFlow();
        for (Edge fromEdge : fromEdges) {
            Integer capacity = fromEdge.getCapacity();
            if (flow == null) {
                if (capacity > 0) {
                    EdgeFlow edgeFlow = new EdgeFlow(fromEdge, capacity);
                    path.add(edgeFlow);
                    path = findPath(fromEdge.getTo(), to, path);
                    EdgeFlow lastEdge = path.getLast();
                    if (!lastEdge.equals(edgeFlow) && lastEdge.getFlow() < edgeFlow.getFlow()) {
                        edgeFlow.setFlow(lastEdge.getFlow());
                    }
                } else if (capacity == 0) {
                    EdgeFlow edgeFlow = new EdgeFlow(fromEdge, 0);
                    path.add(edgeFlow);
                }
            } else if (capacity < flow) {
                EdgeFlow edgeFlow = new EdgeFlow(fromEdge, capacity);
                path.add(edgeFlow);
                path = findPath(fromEdge.getTo(), to, path);
                EdgeFlow lastEdge = path.getLast();
                if (!lastEdge.equals(edgeFlow) && lastEdge.getFlow() < edgeFlow.getFlow()) {
                    edgeFlow.setFlow(lastEdge.getFlow());
                }
            } else if (capacity >= flow) {
                EdgeFlow edgeFlow = new EdgeFlow(fromEdge, flow);
                path.add(edgeFlow);
                path = findPath(fromEdge.getTo(), to, path);
                EdgeFlow lastEdge = path.getLast();
                if (!lastEdge.equals(edgeFlow) && lastEdge.getFlow() < edgeFlow.getFlow()) {
                    edgeFlow.setFlow(lastEdge.getFlow());
                }
            } else {
                EdgeFlow edgeFlow = new EdgeFlow(fromEdge, 0);
                path.add(edgeFlow);
            }
        }
        return path;
    }
}
