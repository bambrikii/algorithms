package org.bambrikii.examples.graphs.maxflow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://brilliant.org/wiki/ford-fulkerson-algorithm/#
 */
public class FordFulkersonAlgo2 {

    private final Map<Integer, List<Edge2>> edges = new HashMap<>();

    public FordFulkersonAlgo2 edge(int from, int to, int val) {
        if (!edges.containsKey(from)) {
            edges.put(from, new ArrayList<>());
        }
        edges.get(from).add(new Edge2(from, to, val, 0));
        return this;
    }

    public LinkedList<Edge2> getPath(Integer start, Integer end, LinkedList<Edge2> path) {
        if (start == end) {
            return path;
        }

        for (Edge2 edge : edges.get(start)) {
            Integer residualCapacity = edge.getCapacity() - edge.getFlow();
            if (residualCapacity > 0 && !path.contains(edge)) {
                edge.setResidualCapacity(residualCapacity);
                LinkedList<Edge2> path2 = new LinkedList<>();
                path2.addAll(path);
                path2.add(edge);
                LinkedList<Edge2> result = getPath(edge.getTo(), end, path2);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    public int calculateMaxFlow(Integer start, Integer end) {
        LinkedList<Edge2> path = getPath(start, end, new LinkedList<>());
        while (path != null && !path.isEmpty()) {
            Integer flow = path.stream().mapToInt(edge -> edge.getResidualCapacity()).min().getAsInt();
            for (Edge2 edge2 : path) {
                edge2.setFlow(edge2.getFlow() + flow);
                Edge returnEdge = edge2.getReturnEdge();
                returnEdge.setFlow(returnEdge.getFlow() - flow);
            }
            path = getPath(start, end, new LinkedList<>());
        }
        return edges.get(start).stream().mapToInt(edge -> edge.getFlow()).sum();
    }

    public void print(Integer start, Integer end) {
        LinkedList<Edge2> queue = new LinkedList<>();
        List<Edge2> edges = this.edges.get(start);
        if (edges == null || edges.isEmpty()) {
            return;
        }
        queue.addAll(edges);
        while (!queue.isEmpty()) {
            Edge2 edge = queue.poll();
            System.out.println(edge);
            edges = this.edges.get(edge.getTo());
            if (edges != null && !edges.isEmpty()) {
                queue.addAll(edges);
            }
        }

        System.out.println("Max flow = " + this.edges.get(start).stream().mapToInt(edge -> edge.getFlow()).sum());
    }
}
