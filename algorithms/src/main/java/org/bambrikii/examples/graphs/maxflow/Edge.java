package org.bambrikii.examples.graphs.maxflow;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"capacity", "flow"})
@ToString
public class Edge {
    private Integer from;
    private Integer to;
    private Integer capacity;
    private Integer flow;
}
