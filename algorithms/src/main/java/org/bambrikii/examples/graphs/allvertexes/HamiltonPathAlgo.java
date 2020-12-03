package org.bambrikii.examples.graphs.allvertexes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * https://youtu.be/AwsMTEl79wI?t=252
 */
public class HamiltonPathAlgo {
    private final Map<Integer, HamiltonPathVertex> vertexes = new HashMap<>();

    public HamiltonPathAlgo undirectedEdge(int from, int to) {
        ensureEdge(from, to);
        ensureEdge(to, from);
        return this;
    }

    public HamiltonPathAlgo directedEdge(int from, int to) {
        ensureEdge(from, to);
        return this;
    }

    private void ensureEdge(int from, int to) {
        if (!vertexes.containsKey(from)) {
            vertexes.put(from, new HamiltonPathVertex());
        }
        vertexes.get(from).addEdge(new HamiltonPathEdge(from, to));
    }

    public List<List<Integer>> find() {
        List<List<Integer>> paths = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        for (Entry<Integer, HamiltonPathVertex> entry : vertexes.entrySet()) {
            Integer vertex = entry.getKey();
            find(vertex, path, paths);
        }
        return paths;
    }

    private void find(Integer vertex, LinkedList<Integer> path, List<List<Integer>> paths) {
        HamiltonPathVertex vertex2 = vertexes.get(vertex);
        vertex2.setVisited(true);
        path.addLast(vertex);
        for (HamiltonPathEdge edge : vertex2.getEdges()) {
            int nextVertex = edge.getTo();
            if (vertexes.get(nextVertex).isVisited()) {
                continue;
            }
            find(nextVertex, path, paths);
        }
        if (path.size() == vertexes.size()) {
            paths.add(new ArrayList<>(path));
        }
        path.removeLast();
        vertex2.setVisited(false);
    }

    public void print(List<List<Integer>> paths) {
        for (List<Integer> path : paths) {
            Integer first = path.get(0);
            Integer last = path.get(path.size() - 1);
            boolean isCycle = vertexes.get(first).getEdges()
                    .stream()
                    .filter(edge -> (edge.getFrom() == first ? edge.getTo() : edge.getFrom()) == last)
                    .findFirst()
                    .isPresent();
            System.out.println((isCycle ? "Cycle" : "Path") + ": " + path);
        }
    }
}
