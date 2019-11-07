package org.bambrikii.examples.graphs.paths.adjacencylist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 03/06/18 17:22.
 */
public class GraphBuilder {
    private List<List<int[]>> items = new ArrayList<>();

    public GraphBuilder edge(int left, int right, int value) {
        int max = Math.max(left, right);
        ensureLeft(max);
        items.get(left).add(new int[]{right, value});
        return this;
    }

    private void ensureLeft(int max) {
        if (items.size() <= max) {
            for (int i = items.size(); i <= max; i++) {
                items.add(new ArrayList<>());
            }
        }
    }

    public int[][][] build() {
        int[][][] graph = new int[items.size()][][];
        for (int i = 0; i < items.size(); i++) {
            List<int[]> right = items.get(i);
            graph[i] = right.toArray(new int[right.size()][]);
        }
        return graph;
    }
}
