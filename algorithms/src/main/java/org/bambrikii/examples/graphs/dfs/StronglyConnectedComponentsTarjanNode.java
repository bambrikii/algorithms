package org.bambrikii.examples.graphs.dfs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@Setter
@ToString
class StronglyConnectedComponentsTarjanNode extends ArrayList<StronglyConnectedComponentsTarjanNode> {
    private int val;
    private int index;

    StronglyConnectedComponentsTarjanNode(int val) {
        this.val = val;
    }
}
