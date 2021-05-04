package org.bambrikii.examples.graphs.mstree.kruskal;

import org.bambrikii.examples.graphs.mstree.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KruskalAlgoMatrix {

    public KruskalAlgoMatrixResult find(int[][] matrix) {
        Set<Integer> processed = new HashSet<>();
        List<Edge> sorted = new ArrayList<>();
        for (int from = 0; from < matrix.length; from++) {
            int[] row = matrix[from];
            for (int to = from + 1; to < row.length; to++) {
                int w = matrix[from][to];
                sorted.add(new Edge(w, from, to));
            }
        }
        Collections.sort(sorted, (o1, o2) -> {
            if (o1.getW() < o2.getW()) {
                return -1;
            }
            if (o1.getW() > o2.getW()) {
                return 1;
            }
            return 0;
        });

        List<Edge> result = new ArrayList<>();

        for (Edge edge : sorted) {
            int from = edge.getFrom();
            int to = edge.getTo();
            if (processed.contains(from) && processed.contains(to)) {
                continue;
            }

            int w2 = matrix[from][to];
            if (w2 == 0) {
                continue;
            }

            processed.add(from);
            processed.add(to);

            result.add(new Edge(matrix[from][to], from, to));
        }
        return new KruskalAlgoMatrixResult(processed, result);
    }

    static class KruskalAlgoMatrixResult {
        private Set<Integer> processed;
        private List<Edge> edges;

        public KruskalAlgoMatrixResult(
                Set<Integer> processed,
                List<Edge> edges
        ) {
            this.processed = processed;
            this.edges = edges;
        }

        public Set<Integer> getProcessed() {
            return processed;
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }
}
