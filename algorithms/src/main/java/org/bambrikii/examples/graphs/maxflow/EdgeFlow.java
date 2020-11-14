package org.bambrikii.examples.graphs.maxflow;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = "flow")
@ToString
public class EdgeFlow {
    private Edge edge;
    private Integer flow;
}
