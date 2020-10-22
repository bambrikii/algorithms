package org.bambrikii.examples.hashmap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class HashMapPerformanceTest2 {
    public static List<Object> data() {
        List<Object> data = new ArrayList<>();
        for (int size = HashBasedMapPerformanceTest.SAMPLE_SIZE; size <= HashBasedMapPerformanceTest.SAMPLE_SIZE; size += 1_000) {
            for (double ratio = 0.75; ratio <= 1.2; ratio += 0.05) {
                data.add(new Object[]{size, ratio});
            }
        }
        return data;
    }

    private HashMap<Integer, Integer> before(int size, double ratio) {
        HashMap<Integer, Integer> map = new HashMap<>(0, (float) ratio);
        for (int i = 0; i < size; i++) {
            map.put(i, i);
        }
        return map;
    }

    @AfterEach
    public void after() {
    }

    @ParameterizedTest(name = "add {index}: size={0}, ratio={1}")
    @MethodSource("data")
    public void should1Add(int size, double ratio) {
        HashMap<Integer, Integer> map = before(size, ratio);

        for (int i = 0; i < size; i++) {
            map.put(i, i);
        }
    }

    @ParameterizedTest(name = "find {index}: size={0}, ratio={1}")
    @MethodSource("data")
    public void should2Find(int size, double ratio) {
        HashMap<Integer, Integer> map = before(size, ratio);

        for (int i = 0; i < size; i++) {
            map.get(i);
        }
    }

    @ParameterizedTest(name = "remove {index}: size={0}, ratio={1}")
    @MethodSource("data")
    public void should3Remove(int size, double ratio) {
        HashMap<Integer, Integer> map = before(size, ratio);

        for (int i = 0; i < size; i++) {
            map.remove(i);
        }
    }
}
