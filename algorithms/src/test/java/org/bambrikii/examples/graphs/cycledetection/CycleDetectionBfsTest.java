package org.bambrikii.examples.graphs.cycledetection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CycleDetectionBfsTest {
    private CycleDetectionBfs algo;

    @BeforeEach
    public void before() {
        algo = new CycleDetectionBfs();
    }

    @Test
    public void shouldNotHaveCycles() {
        boolean hasCycles = algo
                .edge(1, 2)
                .edge(2, 3)
                .edge(1, 3)
                .edge(3, 4)
                .find()
                .print();

        assertThat(hasCycles).isFalse();
    }

    @Test
    public void shouldHaveCycles() {
        boolean hasCycles = algo
                .edge(1, 2)
                .edge(2, 3)
                .edge(3, 4)
                .edge(3, 1)
                .find()
                .print();

        assertThat(hasCycles).isTrue();
    }
}
