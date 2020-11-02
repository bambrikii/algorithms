package org.bambrikii.examples.mstree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KruskalAlgoMatrix {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    static class Edge {
        int w;
        int from;
        int to;
    }

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
            if (o1.w < o2.w) {
                return -1;
            }
            if (o1.w > o2.w) {
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

    @Getter
    @Setter
    @AllArgsConstructor
    static class KruskalAlgoMatrixResult {
        private Set<Integer> processed;
        private List<Edge> edges;
    }
}
