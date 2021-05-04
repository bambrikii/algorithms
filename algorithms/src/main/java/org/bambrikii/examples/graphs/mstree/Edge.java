package org.bambrikii.examples.graphs.mstree;

public class Edge {
    private int w;
    private int from;
    private int to;

    public Edge(int w, int from, int to) {
        this.w = w;
        this.from = from;
        this.to = to;
    }

    public int getW() {
        return w;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Edge{w=" + w + ", from=" + from + ", to=" + to + '}';
    }
}
