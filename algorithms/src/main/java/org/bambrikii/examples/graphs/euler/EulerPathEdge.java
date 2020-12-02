package org.bambrikii.examples.graphs.euler;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EulerPathEdge {
    private boolean visited = false;
    private Integer from;
    private Integer to;

    public EulerPathEdge(Integer from, Integer to) {
        this.from = from;
        this.to = to;
    }

    public static EulerPathEdge directed(int from, int to) {
        return new EulerPathEdge(from, to);
    }

    public Integer other(Integer side) {
        if (from.equals(side)) {
            return to;
        }
        return from;
    }

    public static EulerPathEdge undirected(Integer from, Integer to) {
        if (from <= to) {
            return new EulerPathEdge(from, to);
        }
        return new EulerPathEdge(to, from);
    }
}
