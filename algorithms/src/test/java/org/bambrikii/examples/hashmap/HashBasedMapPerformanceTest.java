package org.bambrikii.examples.hashmap;

import org.bambrikii.examples.graphs.hashmap.HashBasedMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class HashBasedMapPerformanceTest {
    public static final int SAMPLE_SIZE = 1_000_000;

    private HashExtractor hash = new HashExtractor();

    public static List<Object> data() {
        List<Object> data = new ArrayList<>();
        for (int size = SAMPLE_SIZE; size <= SAMPLE_SIZE; size += 1_000) {
            for (double ratio = 0.85; ratio < 0.95; ratio += 0.05) {
                for (double threshold = 1.8; threshold <= 2.1; threshold += 0.1) {
                    data.add(new Object[]{size, ratio, threshold});
                }
            }
        }
        return data;
    }

    private HashBasedMap<Integer, Integer> before(int size, double ratio, double threshold) {
        HashBasedMap<Integer, Integer> map = new HashBasedMap<Integer, Integer>(hash, ratio, threshold);
        for (int i = 0; i < size; i++) {
            map.add(i, i);
        }
        return map;
    }

    @DisplayName("add {index}: size={0}, ratio={1}, threshold={2}")
    @ParameterizedTest
    @MethodSource("data")
    public void should1Add(int size, double ratio, double threshold) {
        HashBasedMap<Integer, Integer> map = before(size, ratio, threshold);

        for (int i = 0; i < size; i++) {
            map.add(i, i);
        }
    }

    @DisplayName("find {index}: size={0}, ratio={1}, threshold={2}")
    @ParameterizedTest
    @MethodSource("data")
    public void should2Find(int size, double ratio, double threshold) {
        HashBasedMap<Integer, Integer> map = before(size, ratio, threshold);

        for (int i = 0; i < size; i++) {
            map.find(i);
        }
    }

    @DisplayName("remove {index}: size={0}, ratio={1}, threshold={2}")
    @ParameterizedTest
    @MethodSource("data")
    public void should3Remove(int size, double ratio, double threshold) {
        HashBasedMap<Integer, Integer> map = before(size, ratio, threshold);

        for (int i = 0; i < size; i++) {
            map.remove(i);
        }
    }
}
