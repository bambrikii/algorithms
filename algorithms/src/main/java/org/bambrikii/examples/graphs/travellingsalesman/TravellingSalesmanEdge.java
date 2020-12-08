package org.bambrikii.examples.graphs.travellingsalesman;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TravellingSalesmanEdge {
    private final int from;
    private final int to;
    private final int weight;
    @Setter
    private boolean visited;

    public TravellingSalesmanEdge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public static TravellingSalesmanEdge directed(int from, int to, int weight) {
        return new TravellingSalesmanEdge(from, to, weight);
    }

    public static TravellingSalesmanEdge undirected(int from, int to, int weight) {
        if (from <= to) {
            return new TravellingSalesmanEdge(from, to, weight);
        }
        return new TravellingSalesmanEdge(to, from, weight);
    }
}
