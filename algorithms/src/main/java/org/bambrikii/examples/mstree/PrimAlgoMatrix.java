package org.bambrikii.examples.mstree;

import java.util.ArrayList;
import java.util.List;

public class PrimAlgoMatrix {
    public List<KruskalAlgoMatrix.Edge> find(int[][] matrix) {
        List<KruskalAlgoMatrix.Edge> edges = new ArrayList<>();
        int from = 0;
        while (true) {
            int w = 0;
            int to = -1;
            int[] rows = matrix[from];
            for (int to2 = from + 1; to2 < rows.length; to2++) {
                int w2 = rows[to2];
                if (w2 == 0) {
                    continue;
                }
                if (w == 0 || w2 < w) {
                    w = w2;
                    to = to2;
                }
            }
            if (to == -1) {
                break;
            }
            edges.add(new KruskalAlgoMatrix.Edge(w, from, to));
            from = to;
        }
        return edges;
    }
}
