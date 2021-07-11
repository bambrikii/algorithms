package org.bambrikii.examples.numbers.prime;

import org.bambrikii.examples.numbers.KaratsubaMultiplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class KaratsubaMultiplicationTest {
    private KaratsubaMultiplication algo;

    public static Collection<Object[]> params() {
        List<Object[]> params = new ArrayList<>();
        params.add(new Object[]{"2", "3", "6"});
        params.add(new Object[]{"20", "30", "600"});
        params.add(new Object[]{"200", "300", "60000"});
        params.add(new Object[]{"123", "456", "56088"});
        params.add(new Object[]{"546546564", "549848421", "300517765218375444"});
        params.add(new Object[]{"846546", "25654", "21717291084"});
        params.add(new Object[]{"-2", "3", "-6"});
        params.add(new Object[]{"2", "-3", "-6"});
        params.add(new Object[]{"-2", "-3", "6"});
        return params;
    }

    @BeforeEach
    public void before() {
        algo = new KaratsubaMultiplication();
    }

    @ParameterizedTest
    @MethodSource("params")
    public void shouldMultiply(String str1, String str2, String expected) {
        assertThat(algo.multiply(str1, str2)).isEqualTo(expected);
    }
}
