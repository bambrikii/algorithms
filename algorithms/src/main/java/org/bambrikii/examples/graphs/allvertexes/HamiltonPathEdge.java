package org.bambrikii.examples.graphs.allvertexes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HamiltonPathEdge {
    private int from;
    private int to;

    public HamiltonPathEdge(int from, int to) {
        this.from = from;
        this.to = to;
    }
}
