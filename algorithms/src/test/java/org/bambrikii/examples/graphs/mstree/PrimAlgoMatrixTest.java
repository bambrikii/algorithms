package org.bambrikii.examples.graphs.mstree;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.bambrikii.examples.graphs.mstree.KruskalAlgoMatrixTest.buildInput;
import static org.bambrikii.examples.graphs.mstree.KruskalAlgoMatrixTest.printOutput;

public class PrimAlgoMatrixTest {
    @Test
    public void shouldFind() {
        PrimAlgoMatrix algo = new PrimAlgoMatrix();

        List<KruskalAlgoMatrix.Edge> result = algo.find(buildInput());

        printOutput(result);
    }
}
