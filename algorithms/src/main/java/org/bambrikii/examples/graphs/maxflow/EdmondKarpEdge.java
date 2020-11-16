package org.bambrikii.examples.graphs.maxflow;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"flow", "prev"})
@ToString
class EdmondKarpEdge {
    private Integer from;
    private Integer to;
    private Integer capacity;
    private Integer flow;
    private EdmondKarpEdge prev;

    public EdmondKarpEdge(Integer from, Integer to, Integer capacity, Integer flow) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = flow;
    }
}
