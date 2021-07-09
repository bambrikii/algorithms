package org.bambrikii.examples.matrix;

import org.bambrikii.examples.martix.KadeneAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KadeneAlgorithmTest {
    private KadeneAlgorithm algo;

    @BeforeEach
    public void before() {
        this.algo = new KadeneAlgorithm();
    }

    public static Collection<Object[]> params() {
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}, 6});
        return params;
    }

    @ParameterizedTest
    @MethodSource("params")
    public void shouldFindMaxSum(int[] arr, int expected) {
        assertThat(algo.fndGlobalMaximum(arr)).isEqualTo(expected);
    }
}
