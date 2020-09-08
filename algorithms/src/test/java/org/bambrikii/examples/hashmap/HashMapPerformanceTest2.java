package org.bambrikii.examples.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HashMapPerformanceTest2 {
	@Parameters(name = "case {index}: size={0}, ratio={1}")
	public static List<Object> data() {
		List<Object> data = new ArrayList<>();
		for (int size = HashBasedMapPerformanceTest.SAMPLE_SIZE; size <= HashBasedMapPerformanceTest.SAMPLE_SIZE; size += 1_000) {
			for (double ratio = 0.75; ratio <= 1.2; ratio += 0.05) {
				data.add(new Object[] { size, ratio });
			}
		}
		return data;
	}

	private HashMap<Integer, Integer> map;

	@Parameter(0)
	public static int size;

	@Parameter(1)
	public static double ratio;

	@Before
	public void before() {
		map = new HashMap<>(0, (float) ratio);
		for (int i = 0; i < size; i++) {
			map.put(i, i);
		}
	}

	@After
	public void after() {
	}

	@Test
	public void should1Add() {
		for (int i = 0; i < size; i++) {
			map.put(i, i);
		}
	}

	@Test
	public void should2Find() {
		for (int i = 0; i < size; i++) {
			map.get(i);
		}
	}

	@Test
	public void should3Remove() {
		for (int i = 0; i < size; i++) {
			map.remove(i);
		}
	}
}
