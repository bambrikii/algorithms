package org.bambrikii.examples.algorithms.incubator.graphs.minimumspanningtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractMinimumSpanningTreeAlgo {
    public abstract List<Edge> build();

    record Edge(int from, int to, int weight) {
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

    final Map<Integer, List<Edge>> edges = new HashMap<>();

    public <T extends AbstractMinimumSpanningTreeAlgo> T edge(int from, int to, int weight) {
        ensureEdge(from, to, weight);
        ensureEdge(to, from, weight);
        return (T) this;
    }

    private void ensureEdge(int from, int to, int weight) {
        if (!edges.containsKey(from)) {
            edges.put(from, new ArrayList<>());
        }
        List<Edge> edges = this.edges.get(from);
        edges.add(new Edge(from, to, weight));
    }

    void print(List<Edge> result) {
        System.out.println("---");
        for (Edge edge : result) {
            System.out.println(edge);
        }
    }
}
