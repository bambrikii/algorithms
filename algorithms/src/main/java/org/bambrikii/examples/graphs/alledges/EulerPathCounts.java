package org.bambrikii.examples.graphs.alledges;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EulerPathCounts {
    private int from;
    private int to;

    public void from() {
        from++;
    }

    public void to() {
        to++;
    }
}
