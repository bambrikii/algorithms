package org.bambrikii.examples.algorithms.incubator.graphs.minimumspanningtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class PrimsAlgo {
    private static class Edge {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" + "from=" + from + ", to=" + to + ", weight=" + weight + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    private Map<Integer, List<Edge>> edges = new HashMap<>();

    public static void main(String[] args) {
        PrimsAlgo algo = new PrimsAlgo()
                .edge(2, 3, 1)
                .edge(2, 4, 3)
                .edge(3, 4, 5)
                .edge(3, 5, 2)
                .edge(7, 5, 11);
        List<Edge> result = algo.build();
        algo.print(result);
    }

    public PrimsAlgo edge(int from, int to, int weight) {
        ensureEdge(from, to, weight);
        ensureEdge(to, from, weight);
        return this;
    }

    private void ensureEdge(int from, int to, int weight) {
        if (!edges.containsKey(from)) {
            edges.put(from, new ArrayList<>());
        }
        List<Edge> edges = this.edges.get(from);
        edges.add(new Edge(from, to, weight));
    }

    private void print(List<Edge> result) {
        System.out.println("---");
        for (Edge edge : result) {
            System.out.println(edge);
        }
    }

    /**
     * Visit all unvisited yet <b>vertexes</b>,
     * add them to <b>visited</b> list,
     * and try to advance via min weight <b>edges</b> to unvisited vertexes
     * add unvisited vertexes to <b>visited</b> list
     * <p>
     * https://www.youtube.com/watch?v=cplfcGZmX7I
     *
     * @return
     */
    public List<Edge> build() {
        List<Edge> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        while (true) {
            Integer from = findFirst(visited);
            if (from == null) {
                break;
            }
            visited.add(from);
            visit(visited, result);
        }
        return result;
    }

    private Integer findFirst(Set<Integer> visited) {
        for (Map.Entry<Integer, List<Edge>> entry : edges.entrySet()) {
            Integer from = entry.getKey();
            if (!visited.contains(from)) {
                return from;
            }
        }
        return null;
    }

    private void visit(Set<Integer> visited, List<Edge> result) {
        Edge min = null;
        for (Integer from : visited) {
            if (!edges.containsKey(from)) {
                continue;
            }
            for (Edge edge : edges.get(from)) {
                if (visited.contains(edge.to)) {
                    continue;
                }
                if (min == null || min.weight > edge.weight) {
                    min = edge;
                }
            }
        }
        if (min != null) {
            result.add(min);
            visited.add(min.to);
            visit(visited, result);
        }
    }
}
