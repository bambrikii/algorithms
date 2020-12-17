package org.bambrikii.examples.combinatorics.permutations;

import org.junit.jupiter.api.Test;

public class PermutationsNonRecursiveTest {
    @Test
    public void shouldGenerate() {
        new PermutationsNonRecursive().permute(new int[]{1, 2, 3, 4});
    }
}
