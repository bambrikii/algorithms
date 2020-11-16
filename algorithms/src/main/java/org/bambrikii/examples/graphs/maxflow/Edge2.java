package org.bambrikii.examples.graphs.maxflow;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"flow", "residualCapacity"})
@ToString
class Edge2 {
    private Integer from;
    private Integer to;
    private Integer capacity;
    private Integer flow;
    private Integer residualCapacity = 0;

    public Edge2(Integer from, Integer to, Integer capacity, Integer flow) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = flow;
    }
}
